package menu.service;

import java.util.List;
import java.util.stream.Collectors;
import menu.model.coach.Coach;
import menu.repository.CoachRepository;

public class CoachService {
    private final CoachRepository coachRepository;

    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public void registerCoaches(List<String> coachesInput) {
        for (String name : coachesInput) {
            coachRepository.save(new Coach(name));
        }
    }
}
