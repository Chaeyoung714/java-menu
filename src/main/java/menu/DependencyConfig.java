package menu;

import menu.controller.MenuController;
import menu.repository.MenuRepository;
import menu.view.InputHandler;
import menu.view.InputView;
import menu.view.OutputView;

public class DependencyConfig {
    private final MenuRepository menuRepository = new MenuRepository();

    public MenuController controller() {
        return new MenuController(
                new InputHandler(new InputView())
                , new OutputView()
        );
    }
}
