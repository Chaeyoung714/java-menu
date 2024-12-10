package menu.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import menu.exception.customExceptions.DuplicatedCategoryException;
import menu.exception.customExceptions.DuplicatedMenuException;
import menu.exception.customExceptions.ForbiddenMenuException;
import menu.exception.RetryHandler;
import menu.model.lunch.Category;
import menu.model.coach.Coach;
import menu.model.lunch.Menu;
import menu.model.recommendation.Weekday;
import menu.model.recommendation.WeeklyCategories;
import menu.model.recommendation.WeeklyMenus;
import menu.model.recommendation.WeeklyRecommendation;
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
        return RetryHandler.retryRecommendationUntilSuccessAndReturn(() -> {
            Category randomCategory = Category.findByOrdinal(Randoms.pickNumberInRange(1, 5));
            if (Collections.frequency(recommendedStatus.values(), randomCategory) == 2) {
                throw new DuplicatedCategoryException();
            }
            return randomCategory;
        });
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
        return RetryHandler.retryRecommendationUntilSuccessAndReturn(() -> {
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
        });
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
