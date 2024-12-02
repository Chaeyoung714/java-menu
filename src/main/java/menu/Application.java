package menu;

import menu.controller.MenuController;

public class Application {
    public static void main(String[] args) {
        DependencyConfig config = new DependencyConfig();
        MenuController controller = config.menuController();
        controller.run();
    }
}
