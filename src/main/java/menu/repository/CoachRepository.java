package menu.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import menu.model.Coach;

public class CoachRepository {
    private final List<Coach> coaches;

    public CoachRepository() {
        this.coaches = new ArrayList<>();
    }

    public void save(Coach coach) {
        this.coaches.add(coach);
    }

    public List<Coach> findAll() {
        return Collections.unmodifiableList(coaches);
    }
}
