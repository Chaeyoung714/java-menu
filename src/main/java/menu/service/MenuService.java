package menu.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import menu.exception.DuplicatedCategoryException;
import menu.exception.DuplicatedMenuException;
import menu.exception.ForbiddenMenuException;
import menu.model.Category;
import menu.model.Coach;
import menu.model.Menu;
import menu.model.Weekday;
import menu.model.WeeklyCategories;
import menu.model.WeeklyMenus;
import menu.model.WeeklyRecommendation;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;

public class MenuService {
    private final CoachRepository coachRepository;
    private final MenuRepository menuRepository;

    public MenuService(CoachRepository coachRepository, MenuRepository menuRepository) {
        this.coachRepository = coachRepository;
        this.menuRepository = menuRepository;
    }

    public WeeklyCategories recommendCategory() {
        Map<Weekday, Category> recommendedCategories = new HashMap<>();
        for (Weekday weekday : Weekday.getInOrder()) {
            Category category = pickRandomCategory(recommendedCategories);
            recommendedCategories.put(weekday, category);
        }
        return new WeeklyCategories(recommendedCategories);
    }

    private Category pickRandomCategory(Map<Weekday, Category> recommendedStatus) {
        while (true) {
            try {
                Category randomCategory = Category.findByOrdinal(Randoms.pickNumberInRange(1, 5));
                if (Collections.frequency(recommendedStatus.values(), randomCategory) == 2) {
                    throw new DuplicatedCategoryException();
                }
                return randomCategory;
            } catch (DuplicatedCategoryException e) {
            }
        }
    }

    public WeeklyRecommendation recommendMenu(WeeklyCategories weeklyCategories) {
        Map<Coach, Map<Weekday, Menu>> recommendStatus = new HashMap<>();
        for (Weekday weekday : Weekday.getInOrder()) {
            Category category = weeklyCategories.findByWeekday(weekday);
            List<String> targetMenuNames = menuRepository.findByCategory(category).stream()
                    .map(m -> m.getName())
                    .collect(Collectors.toList());
            for (Coach coach : coachRepository.findAll()) {
                Map<Weekday, Menu> recommendStatusPerCoach = findRecommendStatusOfCoach(recommendStatus, coach);
                Menu menu = pickRandomMenu(targetMenuNames, recommendStatus, weekday, coach);
                recommendStatusPerCoach.put(weekday, menu);
            }
        }
        return createResult(recommendStatus);
    }

    private Menu pickRandomMenu(List<String> targetMenuNames, Map<Coach, Map<Weekday, Menu>> recommendStatus, Weekday weekday, Coach coach) {
        while (true) {
            try {
                Menu randomMenu = menuRepository.findByName(Randoms.shuffle(targetMenuNames).get(0));
                List<Menu> recommendedMenusInSameWeek = recommendStatus.keySet().stream()
                        .filter(c -> recommendStatus.get(c).containsKey(weekday))
                        .map(c -> recommendStatus.get(c).get(weekday))
                        .collect(Collectors.toList());
                if (recommendedMenusInSameWeek.contains(randomMenu)) {
                    throw new DuplicatedMenuException();
                }
                if (coach.containsForbiddenMenu(randomMenu)) {
                    throw new ForbiddenMenuException();
                }
                return randomMenu;
            } catch (DuplicatedMenuException | ForbiddenMenuException exception) {
            }
        }
    }

    private Map<Weekday, Menu> findRecommendStatusOfCoach(Map<Coach, Map<Weekday, Menu>> recommendStatus, Coach coach) {
        if (recommendStatus.keySet().contains(coach)) {
            return recommendStatus.get(coach);
        }
        Map<Weekday, Menu> defaultStatus = new HashMap<>();
        recommendStatus.put(coach, defaultStatus);
        return defaultStatus;
    }

    private WeeklyRecommendation createResult(Map<Coach, Map<Weekday, Menu>> recommendStatus) {
        Map<Coach, WeeklyMenus> recommendResult = new HashMap<>();
        for (Coach coach : coachRepository.findAll()) {
            recommendResult.put(coach, new WeeklyMenus(recommendStatus.get(coach)));
        }
        return new WeeklyRecommendation(recommendResult);
    }
}
