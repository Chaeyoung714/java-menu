package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class InputView {
    private static final String PARSING_DELIMITER = ",";

    public void inputStartLine() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public List<String> inputNames() {
        System.out.println(System.lineSeparator()
                + String.format("코치의 이름을 입력해 주세요. (%s 로 구분)", PARSING_DELIMITER));
        String answer = Console.readLine();
        return parseNames(answer);
    }

    private List<String> parseNames(String answer) {
        List<String> parsedNames = List.of(answer.split(PARSING_DELIMITER, -1));
        InputValidator.validateNames(parsedNames);
        return parsedNames;
    }

    public List<String> inputForbiddenMenuNames(String coachName) {
        System.out.println(System.lineSeparator() + String.format("%s(이)가 못 먹는 메뉴를 입력해 주세요.", coachName));
        String answer = Console.readLine();
        return parseMenus(answer);
    }

    private List<String> parseMenus(String answer) {
        if (answer == null || answer.isBlank()) {
            return new ArrayList<>();
        }
        List<String> parsedMenus = List.of(answer.split(PARSING_DELIMITER, -1));
        InputValidator.validateMenuNames(parsedMenus);
        return parsedMenus;
    }
}
