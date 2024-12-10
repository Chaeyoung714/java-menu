package menu;

import menu.controller.MenuController;
import menu.view.InputHandler;
import menu.view.InputView;
import menu.view.OutputView;

public class DependencyConfig {

    public MenuController controller() {
        return new MenuController(
                new InputHandler(new InputView())
                , new OutputView()
        );
    }
}
