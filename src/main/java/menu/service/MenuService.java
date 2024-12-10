package menu.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashMap;
import java.util.Map;
import menu.model.Category;
import menu.model.Weekday;
import menu.model.WeeklyCategories;
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

    public void recommendMenu() {

    }
}
