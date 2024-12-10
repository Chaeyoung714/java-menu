package menu.service;

import java.util.List;
import java.util.stream.Collectors;
import menu.model.Coach;
import menu.model.Menu;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;

public class CoachService {
    private final CoachRepository coachRepository;
    private final MenuRepository menuRepository;

    public CoachService(CoachRepository coachRepository, MenuRepository menuRepository) {
        this.coachRepository = coachRepository;
        this.menuRepository = menuRepository;
    }

    public void registerCoachNames(List<String> coachNames) {
        // TODO : validate
    }

    public void registerCoaches(String coachName, List<String> forbiddenMenuNames) {
        //TODO : validate
        List<Menu> forbiddenMenus = forbiddenMenuNames.stream()
                .map((f) -> menuRepository.findByName(f))
                .collect(Collectors.toList());
        coachRepository.save(new Coach(coachName, forbiddenMenus));
    }

    public void print() {
        for (Coach coach : coachRepository.findAll()) {
            System.out.println(coach.getName());
        }
    }
}
