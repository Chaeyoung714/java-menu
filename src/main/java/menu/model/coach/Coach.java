package menu.model.coach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import menu.model.lunch.Menu;

public class Coach {
    private final String name;
    private final List<Menu> forbiddenMenus;

    public Coach(String name) {
        this.name = name;
        this.forbiddenMenus = new ArrayList<>();
    }

    public void setForbiddenMenuNames(List<Menu> forbiddenMenus) {
        this.forbiddenMenus.addAll(forbiddenMenus);
    }

    public boolean containsForbiddenMenu(Menu randomMenu) {
        return forbiddenMenus.contains(randomMenu);
    }

    public String getName() {
        return name;
    }

    public List<Menu> getForbiddenMenus() {
        return Collections.unmodifiableList(forbiddenMenus);
    }
}
