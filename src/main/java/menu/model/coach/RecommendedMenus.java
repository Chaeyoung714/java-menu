package menu.model.coach;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import menu.model.lunch.Menu;
import menu.model.lunch.Weekday;

public class RecommendedMenus {
    private final Map<Weekday, Menu> menus;

    public RecommendedMenus() {
        this.menus = new HashMap<>();
    }

    public void update(Weekday weekday, Menu randomMenu) {
        if (menus.keySet().contains(weekday)) {
            throw new IllegalStateException();
        }
        menus.put(weekday, randomMenu);
    }

    public boolean containsMenu(Menu randomMenu) {
        return menus.values().contains(randomMenu);
    }

    public Map<Weekday, Menu> getMenus() {
        return Collections.unmodifiableMap(menus);
    }
}
