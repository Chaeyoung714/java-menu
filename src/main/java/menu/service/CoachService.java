package menu.service;

import java.util.List;
import java.util.stream.Collectors;
import menu.model.coach.Coach;
import menu.model.menu.Menu;
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
        } catch (IllegalStateException e) {
            //TODO : 해당 에러를 받아서 재입력 처리 필요
            throw new IllegalArgumentException();
        }
    }
}
