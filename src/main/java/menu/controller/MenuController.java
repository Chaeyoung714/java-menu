package menu.controller;

import java.util.List;
import java.util.Map;
import menu.exception.ErrorHandler;
import menu.model.coach.Coach;
import menu.model.coach.RecommendedCategories;
import menu.model.coach.RecommendedMenus;
import menu.model.lunch.Weekday;
import menu.service.CoachService;
import menu.service.RecommendationService;
import menu.view.InputHandler;
import menu.view.OutputView;

public class MenuController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final CoachService coachService;
    private final RecommendationService recommendationService;

    public MenuController(InputHandler inputHandler, OutputView outputView, CoachService coachService,
                          RecommendationService recommendationService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.coachService = coachService;
        this.recommendationService = recommendationService;
    }

    public void run() {
        List<String> names = inputHandler.inputNames();
        List<Coach> coaches = coachService.registerCoaches(names);
        for (Coach coach : coaches) {
            registerForbiddenMenuName(coach);
        }
        RecommendedCategories categories = recommendationService.recommendCategories();
        Map<Coach, RecommendedMenus> recommendationResult = recommendationService.recommendMenus(categories);
        outputView.printRecommendationResult(categories, coaches, recommendationResult);
    }

    private void registerForbiddenMenuName(Coach coach) {
        while (true) {
            try {
                List<String> forbiddenMenuNames = inputHandler.inputForbiddenMenuNames(coach.getName());
                coachService.registerForbiddenMenuName(coach, forbiddenMenuNames);
                return;
            } catch (IllegalArgumentException e) {
                ErrorHandler.handleUserError(e);
            }
        }
    }
}
