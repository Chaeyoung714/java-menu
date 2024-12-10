package menu.model.coach;

import java.util.List;
import menu.model.lunch.Menu;

public class Coach {
    private final String name;
    private final List<Menu> forbiddenMenus;

    public Coach(String name, List<Menu> forbiddenMenus) {
        this.name = name;
        this.forbiddenMenus = forbiddenMenus;
    }

    public boolean containsForbiddenMenu(Menu menu) {
        return forbiddenMenus.contains(menu);
    }

    public String getName() {
        return name;
    }

    public List<Menu> getForbiddenMenus() {
        return forbiddenMenus;
    }
}
