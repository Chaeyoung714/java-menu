package menu.model;

import java.util.Map;

public class WeeklyRecommendation {
    private final Map<Coach, WeeklyMenus> recommendations;

    public WeeklyRecommendation(Map<Coach, WeeklyMenus> recommendations) {
        this.recommendations = recommendations;
    }
}
