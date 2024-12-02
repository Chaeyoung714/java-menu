package menu.service;

import static menu.exception.ExceptionMessages.MENU_NOT_EXIST;

import java.util.List;
import java.util.stream.Collectors;
import menu.exception.customExceptions.MenuNotExistsException;
import menu.model.recommendation.Coach;
import menu.model.lunch.Menu;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;

public class CoachService {
    private final CoachRepository coachRepository;
    private final MenuRepository menuRepository;

    public CoachService(CoachRepository coachRepository, MenuRepository menuRepository) {
        this.coachRepository = coachRepository;
        this.menuRepository = menuRepository;
    }

    public void registerForbiddenMenuName(Coach coach, List<String> forbiddenMenuNames) {
        try {
            List<Menu> forbiddenMenus = forbiddenMenuNames.stream()
                    .map(mName -> menuRepository.findByName(mName))
                    .collect(Collectors.toList());
            coach.setForbiddenMenuNames(forbiddenMenus);
        } catch (MenuNotExistsException e) {
            throw new IllegalArgumentException(MENU_NOT_EXIST.getMessage());
        }
    }

    public List<Coach> registerCoaches(List<String> coachesInput) {
        for (String name : coachesInput) {
            coachRepository.save(new Coach(name));
        }
        return coachRepository.findAll();
    }
}
