package menu.view;

import static menu.exception.ExceptionMessages.DUPLICATED_VALUE_INPUT;
import static menu.exception.ExceptionMessages.EMPTY_VALUE_INPUT;
import static menu.exception.ExceptionMessages.WRONG_MENUS_COUNT_INPUT;
import static menu.exception.ExceptionMessages.WRONG_NAMES_COUNT_INPUT;
import static menu.exception.ExceptionMessages.WRONG_NAME_LENGTH_INPUT;

import java.util.HashSet;
import java.util.List;

public class InputValidator {
    private static final int MIN_NAMES_COUNT = 2;
    private static final int MAX_NAMES_COUNT = 5;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 4;
    private static final int MAX_MENUS_COUNT = 2;

    public static void validateNames(List<String> parsedNames) {
        validateNamesCount(parsedNames);
        validateDuplication(parsedNames);
        parsedNames.stream().forEach(n -> validateEachNames(n));
    }

    private static void validateNamesCount(List<String> parsedNames) {
        if (parsedNames.size() < MIN_NAMES_COUNT || parsedNames.size() > MAX_NAMES_COUNT) {
            throw new IllegalArgumentException(WRONG_NAMES_COUNT_INPUT.getMessage());
        }
    }

    private static void validateEachNames(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(EMPTY_VALUE_INPUT.getMessage());
        }
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(WRONG_NAME_LENGTH_INPUT.getMessage());
        }
    }

    public static void validateMenuNames(List<String> parsedMenus) {
        validateMenusCount(parsedMenus);
        validateDuplication(parsedMenus);
        parsedMenus.stream().forEach(n -> validateEachMenus(n));
    }

    private static void validateEachMenus(String menu) {
        if (menu == null || menu.isBlank()) {
            throw new IllegalArgumentException(EMPTY_VALUE_INPUT.getMessage());
        }
    }

    private static void validateMenusCount(List<String> parsedMenus) {
        if (parsedMenus.size() > MAX_MENUS_COUNT) {
            throw new IllegalArgumentException(WRONG_MENUS_COUNT_INPUT.getMessage());
        }
    }

    private static void validateDuplication(List<String> parsedValues) {
        if (new HashSet<>(parsedValues).size() != parsedValues.size()) {
            throw new IllegalArgumentException(DUPLICATED_VALUE_INPUT.getMessage());
        }
    }
}
