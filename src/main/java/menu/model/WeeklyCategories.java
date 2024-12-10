package menu.model;

import java.util.Map;

public class WeeklyCategories {
    private final Map<Weekday, Category> categories;

    public WeeklyCategories(Map<Weekday, Category> categories) {
        this.categories = categories;
    }


}
