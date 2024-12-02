package menu;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import menu.exception.ExceptionMessages;
import menu.model.recommendation.Coach;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;
import menu.service.CoachService;
import menu.view.InputValidator;
import menu.view.InputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuValidationTest {
    private final MenuRepository menuRepository = new MenuRepository();
    private CoachRepository coachRepository;
    private CoachService coachService;

    @BeforeEach
    void setUp() {
        coachRepository = new CoachRepository();
        coachService = new CoachService(coachRepository, menuRepository);
        coachService.registerCoaches(List.of(
                "포비", "로로"
        ));
    }


    @Test
    void 존재하지_않는_메뉴를_입력하면_예외가_발생한다() {
        Coach poby = coachRepository.findByName("포비");

        assertThatThrownBy(() -> coachService.registerForbiddenMenuName(poby, List.of("규규", "규동")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.MENU_NOT_EXIST.getMessage());
    }

}
