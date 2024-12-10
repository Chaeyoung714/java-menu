package menu.model;

import java.util.Map;

public class WeeklyMenus {
    private final Map<Weekday, Menu> menus;

    public WeeklyMenus(Map<Weekday, Menu> menus) {
        this.menus = menus;
    }
}
