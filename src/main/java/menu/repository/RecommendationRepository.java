package menu.repository;

import java.util.HashMap;
import java.util.Map;
import menu.model.coach.Coach;
import menu.model.coach.RecommendedMenus;

public class RecommendationRepository {
    private final Map<Coach, RecommendedMenus> recommendation;

    public RecommendationRepository() {
        this.recommendation = new HashMap<>();
    }

    public void save(Coach coach, RecommendedMenus recommendedMenus) {
        this.recommendation.put(coach, recommendedMenus);
    }

    public Map<Coach, RecommendedMenus> findAll() {
        return recommendation;
    }
}
