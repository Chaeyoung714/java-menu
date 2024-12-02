package menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import menu.exception.ExceptionMessages;
import menu.model.recommendation.Coach;
import menu.model.recommendation.RecommendedCategories;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;
import menu.repository.RecommendationRepository;
import menu.service.CategoryService;
import menu.service.CoachService;
import menu.service.MenuService;
import menu.view.InputValidator;
import menu.view.InputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidationTest {
    private final InputView inputView = new InputView();

    @Test
    void 코치_이름_개수가_2개_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.validateNames(List.of("포비")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.WRONG_NAMES_COUNT_INPUT.getMessage());
    }

    @Test
    void 코치_이름_개수가_5개_초과면_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.validateNames(List.of("포비", "로로", "부부", "매니", "포포", "브브")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.WRONG_NAMES_COUNT_INPUT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"포", "포비로로로"})
    void 코치_이름_글자수가_잘못되면_예외가_발생한다(String wrongName) {
        assertThatThrownBy(() -> InputValidator.validateNames(List.of("디폴트", wrongName)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.WRONG_NAME_LENGTH_INPUT.getMessage());
    }

    @Test
    void 코치_이름이_중복되면_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.validateNames(List.of("포비", "로로", "포비")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.DUPLICATED_VALUE_INPUT.getMessage());
    }

    @Test
    void 메뉴_이름_개수가_2개_초과면_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.validateMenuNames(List.of("규동", "우동", "김밥")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.WRONG_MENUS_COUNT_INPUT.getMessage());
    }

    @Test
    void 메뉴_이름이_중복되면_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.validateMenuNames(List.of("규동", "규동")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.DUPLICATED_VALUE_INPUT.getMessage());
    }
}
