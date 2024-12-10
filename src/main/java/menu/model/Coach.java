package menu.model;

import java.util.List;

public class Coach {
    private final String name;
    private final List<Menu> forbiddenMenus;

    public Coach(String name, List<Menu> forbiddenMenus) {
        this.name = name;
        this.forbiddenMenus = forbiddenMenus;
    }

    public String getName() {
        return name;
    }

    public List<Menu> getForbiddenMenus() {
        return forbiddenMenus;
    }
}
