package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {
    public List<String> inputNames() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
        System.out.println(System.lineSeparator() + "코치의 이름을 입력해 주세요. (, 로 구분)");
        String answer = Console.readLine();
        //TODO : 검증로직 추가
        return List.of(answer.split(","));
    }
}
