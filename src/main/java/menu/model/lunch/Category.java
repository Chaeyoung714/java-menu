package menu.model.lunch;

import java.util.Arrays;

public enum Category {
    JAPANESE("일식", 1),
    KOREAN("한식", 2),
    CHINESE("중식", 3),
    ASIAN("아시안", 4),
    WESTERN("양식", 5),
    ;

    public static final int START_NUMBER = 1;
    public static final int LAST_NUMBER = 5;

    private final String name;
    private final int number;

    Category(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public static Category findByNumber(int number) {
        return Arrays.stream(values())
                .filter(c -> c.getNumber() == number)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
