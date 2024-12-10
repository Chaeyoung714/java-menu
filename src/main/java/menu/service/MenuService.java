package menu.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
            Category category = Category.findByOrdinal(Randoms.pickNumberInRange(1, 5));
            recommendedCategories.put(weekday, category);
        }
        return new WeeklyCategories(recommendedCategories);
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
                Menu menu = menuRepository.findByName(Randoms.shuffle(targetMenuNames).get(0));
                recommendStatusPerCoach.put(weekday, menu);
            }
        }
        return createResult(recommendStatus);
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
