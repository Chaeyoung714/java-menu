package menu.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import menu.exception.RetryHandler;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public List<String> readCoachNames() {
        //TODO : 검증추가
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readCoachNames();
            return parseInputValues(answer);
        });
    }

    public List<String> readForbiddenMenusOf(String coachName) {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readForbiddenMenus(coachName);
            return parseInputValues(answer);
        });
    }

    private List<String> parseInputValues(String answer) {
        String[] parsedAnswer = answer.split(",", -1);
        return Arrays.stream(parsedAnswer).map((p) -> {
            InputValidator.validateCoachName(p);
            return p;
        }).collect(Collectors.toList());
    }
}
