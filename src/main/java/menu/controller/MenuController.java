package menu.controller;

import java.util.List;
import menu.exception.RetryHandler;
import menu.model.recommendation.WeeklyCategories;
import menu.model.recommendation.WeeklyRecommendation;
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
        outputView.printServiceStartLine();
        List<String> coachNames = registerCoachNames();
        registerForbiddenMenusOf(coachNames);
        WeeklyCategories weeklyCategories = menuService.recommendCategory();
        WeeklyRecommendation weeklyRecommendation = menuService.recommendMenu(weeklyCategories);
        outputView.printRecommendations(weeklyRecommendation, weeklyCategories);
    }

    private List<String> registerCoachNames() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            List<String> coachNames = inputHandler.readCoachNames();
            coachService.registerCoachNames(coachNames);
            return coachNames;
        });
    }

    private void registerForbiddenMenusOf(List<String> coachNames) {
        for (String coachName : coachNames) {
            RetryHandler.retryUntilSuccess(() -> {
                List<String> forbiddenMenuNames = inputHandler.readForbiddenMenusOf(coachName);
                coachService.registerCoaches(coachName, forbiddenMenuNames);
            });
        }
    }
}
