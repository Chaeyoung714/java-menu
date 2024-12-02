package menu.service;

import java.util.List;
import java.util.stream.Collectors;
import menu.exception.MenuNotExistsException;
import menu.model.coach.Coach;
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

    //TODO : DTO로 반환하기
    public List<Coach> registerCoaches(List<String> coachesInput) {
        for (String name : coachesInput) {
            coachRepository.save(new Coach(name));
        }
        return coachRepository.findAll();
    }

    public void registerForbiddenMenuName(Coach coach, List<String> forbiddenMenuNames) {
        try {
            List<Menu> forbiddenMenus = forbiddenMenuNames.stream()
                    .map(mName -> menuRepository.findByName(mName))
                    .collect(Collectors.toList());
            coach.setForbiddenMenuNames(forbiddenMenus);
        } catch (MenuNotExistsException e) {
            throw new IllegalArgumentException("존재하지 않는 메뉴입니다. 다시 입력해주세요.");
        }
    }
}
