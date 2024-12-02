package menu.util;

import java.util.ArrayList;
import java.util.List;
import menu.model.menu.Category;
import menu.model.menu.Menu;

public class MenuScanner {

    public static List<Menu> scanMenu(String menuData, Category category) {
        List<Menu> menus = new ArrayList<>();
        for (String name : menuData.split(", ")) {
            menus.add(new Menu(name, category));
        }
        return menus;
    }
}
