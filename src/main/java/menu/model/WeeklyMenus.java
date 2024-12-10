package menu.model;

import java.util.Map;

public class WeeklyMenus {
    private final Map<Weekday, Menu> menus;

    public WeeklyMenus(Map<Weekday, Menu> menus) {
        this.menus = menus;
    }

    public Menu findByWeekday(Weekday weekday) {
        return menus.get(weekday);
    }

    public Map<Weekday, Menu> getMenus() {
        return menus;
    }
}
