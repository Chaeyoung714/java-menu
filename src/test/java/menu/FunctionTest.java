package menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import menu.model.coach.Coach;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;
import menu.service.CoachService;
import org.junit.jupiter.api.Test;

public class FunctionTest {

    @Test
    void 메뉴_받아오기_테스트() {
        MenuRepository menuRepository = new MenuRepository();

        assertThat(menuRepository.findAll().size()).isEqualTo(45);
    }

    @Test
    void 못먹는메뉴_저장_테스트() {
        CoachRepository coachRepository = new CoachRepository();
        MenuRepository menuRepository = new MenuRepository();
        CoachService coachService = new CoachService(coachRepository, menuRepository);
        coachService.registerCoaches(List.of(
                "포비"
        ));

        Coach poby = coachRepository.findByName("포비");

        coachService.registerForbiddenMenuName(poby, List.of("우동", "스시"));

        assertThat(poby.getForbiddenMenus().size()).isEqualTo(2);
        assertThat(poby.getForbiddenMenus().get(0)).isEqualTo(menuRepository.findByName("우동"));
    }

}
