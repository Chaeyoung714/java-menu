package menu.model.lunch;

public class Menu {
    private final String name;
    private final Category category;

    public Menu(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
