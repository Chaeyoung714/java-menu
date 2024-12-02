package menu.controller;

import java.util.List;
import java.util.Map;
import menu.exception.ErrorHandler;
import menu.model.recommendation.Coach;
import menu.model.recommendation.RecommendedCategories;
import menu.model.recommendation.RecommendedMenus;
import menu.service.CategoryService;
import menu.service.CoachService;
import menu.service.MenuService;
import menu.view.InputHandler;
import menu.view.OutputView;

public class MenuController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final CoachService coachService;
    private final MenuService menuService;
    private final CategoryService categoryService;

    public MenuController(InputHandler inputHandler, OutputView outputView, CoachService coachService,
                          MenuService menuService, CategoryService categoryService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.coachService = coachService;
        this.menuService = menuService;
        this.categoryService = categoryService;
    }

    public void run() {
        List<Coach> coaches = registerCoaches();
        registerForbiddenMenuNames(coaches);
        RecommendedCategories categories = categoryService.recommendCategories();
        Map<Coach, RecommendedMenus> recommendationResult = menuService.recommendMenus(categories);
        outputView.printRecommendationResult(categories, coaches, recommendationResult);
    }

    private List<Coach> registerCoaches() {
        List<String> names = inputHandler.inputNames();
        return coachService.registerCoaches(names);
    }

    private void registerForbiddenMenuNames(List<Coach> coaches) {
        for (Coach coach : coaches) {
            registerForbiddenMenuName(coach);
        }
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
