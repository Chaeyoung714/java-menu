package menu.service;

import java.util.HashSet;
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
        if (new HashSet<>(coachNames).size() != coachNames.size()) {
            throw new IllegalArgumentException("중복되지 않은 이름을 입력해주세요.");
        }
        if (coachNames.size() < 2 || coachNames.size() > 5) {
            throw new IllegalArgumentException("2명 이상 5명 이하의 코치 이름을 입력해주세요.");
        }
        coachNames.forEach((name) -> {
            if (name.length() < 2 || name.length() > 4) {
                throw new IllegalArgumentException("길이 2 이상 4 이하의 이름을 입력해주세요.");
            }
        });
    }

    public void registerCoaches(String coachName, List<String> forbiddenMenuNames) {
        //TODO : validate
        try {
            validateMenus(forbiddenMenuNames);
            List<Menu> forbiddenMenus = forbiddenMenuNames.stream()
                    .map((f) -> menuRepository.findByName(f))
                    .collect(Collectors.toList());
            coachRepository.save(new Coach(coachName, forbiddenMenus));
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException("존재하지 않는 메뉴명입니다.");
        }
    }

    private void validateMenus(List<String> forbiddenMenuNames) {
        if (new HashSet<>(forbiddenMenuNames).size() != forbiddenMenuNames.size()) {
            throw new IllegalArgumentException("중복되지 않은 메뉴명을 입력해주세요.");
        }
        if (forbiddenMenuNames.size() > 2) {
            throw new IllegalArgumentException("0개 이상 2개 이하의 메뉴명을 입력해주세요.");
        }
    }
}
