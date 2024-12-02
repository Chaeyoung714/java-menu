package menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import menu.controller.MenuController;
import menu.model.lunch.Weekday;

public class Application {
    public static void main(String[] args) {
        DependencyConfig config = new DependencyConfig();
        MenuController controller = config.menuController();
        controller.run();
    }
}
