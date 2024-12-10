package menu.model.recommendation;

import java.util.Map;
import menu.model.lunch.Menu;

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
