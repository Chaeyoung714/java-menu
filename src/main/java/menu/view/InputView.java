package menu.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readCoachNames() {
        System.out.println(System.lineSeparator() + "코치의 이름을 입력해 주세요. (, 로 구분)");
        return Console.readLine();
    }

    public String readForbiddenMenus(String coachName) {
        System.out.println(System.lineSeparator()
                + String.format("%s(이)가 못 먹는 메뉴를 입력해 주세요.", coachName));
        return Console.readLine();
    }
}
