package menu.view;

import java.util.HashSet;
import java.util.List;

public class InputValidator {

    public static void validateNames(List<String> parsedNames) {
        validateNamesCount(parsedNames);
        validateDuplication(parsedNames);
        parsedNames.stream().forEach(n -> validateEachNames(n));
    }

    private static void validateNamesCount(List<String> parsedNames) {
        if (parsedNames.size() < 2 || parsedNames.size() > 5) {
            throw new IllegalArgumentException("2개 이상 5개 이하의 이름을 입력해주세요.");
        }
    }

    private static void validateEachNames(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("값을 채워주세요.");
        }
        if (name.length() < 2 || name.length() > 4) {
            throw new IllegalArgumentException("2글자 이상 4글자 이하의 이름을 입력해주세요.");
        }
    }

    public static void validateMenuNames(List<String> parsedMenus) {
        validateMenusCount(parsedMenus);
        validateDuplication(parsedMenus);
        parsedMenus.stream().forEach(n -> validateEachMenus(n));
    }

    private static void validateEachMenus(String menu) {
        if (menu == null || menu.isBlank()) {
            throw new IllegalArgumentException("값을 채워주세요.");
        }
    }

    private static void validateMenusCount(List<String> parsedMenus) {
        if (parsedMenus.size() > 2) {
            throw new IllegalArgumentException("0개 이상 2개 이하의 메뉴를 입력해주세요.");
        }
    }

    private static void validateDuplication(List<String> parsedValues) {
        if (new HashSet<>(parsedValues).size() != parsedValues.size()) {
            throw new IllegalArgumentException("중복된 값입니다. 다시 입력해주세요.");
        }
    }
}
