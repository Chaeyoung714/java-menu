package menu.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MenuScanner {

    public static List<String> readMenu(String information) {
        return Arrays.stream(information.split(", "))
                .collect(Collectors.toList());
    }

    private MenuScanner() {
    }
}
