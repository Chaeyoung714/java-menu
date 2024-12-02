package menu.view;

import java.util.List;
import menu.exception.ErrorHandler;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public List<String> inputNames() {
        while (true) {
            try {
                return inputView.inputNames();
            } catch (IllegalArgumentException e) {
                ErrorHandler.handleUserError(e);
            }
        }
    }
}
