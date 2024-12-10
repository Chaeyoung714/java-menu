package menu.model;

import java.util.List;
import java.util.Map;

public class WeeklyCategories {
    private final Map<Weekday, Category> categories;

    public WeeklyCategories(Map<Weekday, Category> categories) {
        this.categories = categories;
    }

    public Category findByWeekday(Weekday weekday) {
        return categories.get(weekday);
    }

    public String getCategoryNameOf(Weekday weekday) {
        return categories.get(weekday).getName();
    }
}
