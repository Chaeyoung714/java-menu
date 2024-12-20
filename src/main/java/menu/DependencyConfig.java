package menu;


import menu.controller.MenuController;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;
import menu.repository.RecommendationRepository;
import menu.service.CategoryService;
import menu.service.CoachService;
import menu.service.MenuService;
import menu.view.InputHandler;
import menu.view.InputView;
import menu.view.OutputView;

public class DependencyConfig {
    private final MenuRepository menuRepository = new MenuRepository();
    private final CoachRepository coachRepository = new CoachRepository();
    private final RecommendationRepository recommendationRepository = new RecommendationRepository();

    private CoachService coachService() {
        return new CoachService(coachRepository, menuRepository);
    }

    private MenuService recommendationService() {
        return new MenuService(coachRepository, menuRepository, recommendationRepository);
    }

    public MenuController menuController() {
        return new MenuController(
                new InputHandler(new InputView())
                , new OutputView()
                , coachService()
                , recommendationService()
                , new CategoryService()
        );
    }
}
