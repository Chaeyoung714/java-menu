package menu.model.lunch;

public enum Weekday {
    MONDAY("월요일"),
    TUESDAY("화요일"),
    WEDNESDAY("수요일"),
    THURSDAY("목요일"),
    FRIDAY("금요일"),
    ;

    private final String name;

    Weekday(String name) {
        this.name = name;
    }
}
