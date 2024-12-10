package menu.model.recommendation;

import java.util.Map;
import menu.model.coach.Coach;

public class WeeklyRecommendation {
    private final Map<Coach, WeeklyMenus> recommendations;

    public WeeklyRecommendation(Map<Coach, WeeklyMenus> recommendations) {
        this.recommendations = recommendations;
    }

    public WeeklyMenus findByCoach(Coach coach) {
        return recommendations.get(coach);
    }

    public Map<Coach, WeeklyMenus> getRecommendations() {
        return recommendations;
    }
}
