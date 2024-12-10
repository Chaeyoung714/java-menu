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
        outputView.printStartLine();
        List<String> coachNames = inputHandler.readCoachNames();
        coachService.registerCoachNames(coachNames);
        for (String coachName : coachNames) {
            List<String> forbiddenMenuNames = inputHandler.readForbiddenMenusOf(coachName);
            coachService.registerCoaches(coachName, forbiddenMenuNames);
        }
        coachService.print();

    }
}
