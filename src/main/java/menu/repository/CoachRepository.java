package menu.repository;

import java.util.ArrayList;
import java.util.List;
import menu.model.coach.Coach;

public class CoachRepository {
    private final List<Coach> coaches;

    public CoachRepository() {
        this.coaches = new ArrayList<>();
    }

    public void save(Coach coach) {
        this.coaches.add(coach);
    }
}
