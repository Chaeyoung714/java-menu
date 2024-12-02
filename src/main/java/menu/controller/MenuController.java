package menu.controller;

import java.util.List;
import menu.service.CoachService;
import menu.view.InputHandler;
import menu.view.OutputView;

public class MenuController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final CoachService coachService;

    public MenuController(InputHandler inputHandler, OutputView outputView, CoachService coachService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.coachService = coachService;
    }

    public void run() {
        List<String> names = inputHandler.inputNames();
        coachService.registerCoaches(names);
    }
}
