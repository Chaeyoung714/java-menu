package menu.view;

public class InputValidator {

    public static void validateCoachName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("값을 입력해주세요.");
        }
    }
}
