package menu.model.coach;

import java.util.Map;
import menu.model.lunch.Menu;
import menu.model.lunch.Weekday;

public class RecommendedMenus {
    private final Map<Weekday, Menu> menus;

    public RecommendedMenus(Map<Weekday, Menu> menus) {
        this.menus = menus;
    }

    public Map<Weekday, Menu> getMenus() {
        return menus;
    }
}
