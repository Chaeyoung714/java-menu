package menu.model.lunch;

import java.util.Arrays;

public enum Category {
    JAPANESE("일식", 1),
    KOREAN("한식", 2),
    CHINESE("중식", 3),
    ASIAN("아시안", 4),
    WESTERN("양식", 5),
    ;

    private final String name;
    private final int ordinal;

    Category(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public static Category findByOrdinal(int ordinal) {
        return Arrays.stream(Category.values())
                .filter(c -> c.getOrdinal() == ordinal)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public String getName() {
        return name;
    }

    private int getOrdinal() {
        return ordinal;
    }
}
