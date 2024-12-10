package menu.view;

public class InputValidator {

    public static void validateInputValue(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("값을 입력해주세요.");
        }
    }
}
