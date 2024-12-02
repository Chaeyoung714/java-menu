package menu.model.coach;

import java.util.ArrayList;
import java.util.List;
import menu.model.menu.Menu;

public class Coach {
    private final String name;
    private final List<Menu> refusingMenus;

    public Coach(String name) {
        this.name = name;
        this.refusingMenus = new ArrayList<>();
    }
}
