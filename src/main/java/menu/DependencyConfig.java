package menu;


import menu.controller.MenuController;
import menu.repository.MenuRepository;
import menu.view.InputHandler;
import menu.view.OutputView;

public class DependencyConfig {
    private final MenuRepository menuRepository = new MenuRepository();

    public MenuController menuController() {
        return new MenuController(
                new InputHandler()
                , new OutputView()
        );
    }
}
