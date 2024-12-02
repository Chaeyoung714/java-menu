package menu.model.lunch;

import java.util.List;

public enum Weekday {
    MONDAY("월요일"),
    TUESDAY("화요일"),
    WEDNESDAY("수요일"),
    THURSDAY("목요일"),
    FRIDAY("금요일"),
    ;

    public static final int COUNT = 5;

    private final String name;

    Weekday(String name) {
        this.name = name;
    }

    public static List<Weekday> getAllInOrder() {
        return List.of(Weekday.values());
    }

    public String getName() {
        return name;
    }
}
