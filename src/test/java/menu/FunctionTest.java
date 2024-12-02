package menu;

import static org.assertj.core.api.Assertions.assertThat;

import menu.repository.MenuRepository;
import org.junit.jupiter.api.Test;

public class FunctionTest {

    @Test
    void 메뉴_받아오기_테스트() {
        MenuRepository menuRepository = new MenuRepository();

        assertThat(menuRepository.findAll().size()).isEqualTo(45);
    }
}
