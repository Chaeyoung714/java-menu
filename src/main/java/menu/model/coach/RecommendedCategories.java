package menu.model.coach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import menu.model.lunch.Category;
import menu.model.lunch.Weekday;

public class RecommendedCategories {
    private final Map<Weekday, Category> categories;

    private RecommendedCategories(Map<Weekday, Category> categories) {
        this.categories = categories;
    }

    public static RecommendedCategories of(List<Category> categories) {
        Map<Weekday, Category> categoriesPerDay = new HashMap<>();
        int weekdayCount = 0;
        for (Weekday weekday : Weekday.getAllInOrder()) {
            categoriesPerDay.put(weekday, categories.get(weekdayCount++));
        }
        return new RecommendedCategories(categoriesPerDay);
    }

    public Category findByWeekday(Weekday weekday) {
        return categories.get(weekday);
    }
}
