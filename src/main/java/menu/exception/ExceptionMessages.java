package menu.exception;

public enum ExceptionMessages {
    RECOMMEND_SYSTEM_FAIL("추천에 실패했습니다."),
    MENU_NOT_EXIST("존재하지 않는 메뉴입니다. 다시 입력해주세요."),
    WRONG_NAMES_COUNT_INPUT("2개 이상 5개 이하의 이름을 입력해주세요."),
    EMPTY_VALUE_INPUT("값을 채워주세요."),
    WRONG_NAME_LENGTH_INPUT("2글자 이상 4글자 이하의 이름을 입력해주세요."),
    WRONG_MENUS_COUNT_INPUT("0개 이상 2개 이하의 메뉴를 입력해주세요."),
    DUPLICATED_VALUE_INPUT("중복된 값입니다. 다시 입력해주세요."),
    ;

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
