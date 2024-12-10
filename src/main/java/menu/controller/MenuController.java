package menu.controller;

import java.util.List;
import menu.model.WeeklyCategories;
import menu.model.WeeklyRecommendation;
import menu.service.CoachService;
import menu.service.MenuService;
import menu.view.InputHandler;
import menu.view.OutputView;

public class MenuController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final CoachService coachService;
    private final MenuService menuService;

    public MenuController(InputHandler inputHandler, OutputView outputView, CoachService coachService,
                          MenuService menuService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.coachService = coachService;
        this.menuService = menuService;
    }

    public void run() {
        outputView.printStartLine();
        List<String> coachNames = inputHandler.readCoachNames();
        coachService.registerCoachNames(coachNames);
        for (String coachName : coachNames) {
            List<String> forbiddenMenuNames = inputHandler.readForbiddenMenusOf(coachName);
            coachService.registerCoaches(coachName, forbiddenMenuNames);
        }
        WeeklyCategories weeklyCategories = menuService.recommendCategory();
        WeeklyRecommendation weeklyRecommendation = menuService.recommendMenu(weeklyCategories);


    }
}
