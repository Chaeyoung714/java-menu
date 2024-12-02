package menu;


import menu.controller.MenuController;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;
import menu.service.CoachService;
import menu.service.RecommendationService;
import menu.view.InputHandler;
import menu.view.InputView;
import menu.view.OutputView;

public class DependencyConfig {
    private final MenuRepository menuRepository = new MenuRepository();
    private final CoachRepository coachRepository = new CoachRepository();

    private CoachService coachService() {
        return new CoachService(coachRepository, menuRepository);
    }

    private RecommendationService recommendationService() {
        return new RecommendationService(coachRepository, menuRepository);
    }

    public MenuController menuController() {
        return new MenuController(
                new InputHandler(new InputView())
                , new OutputView()
                , coachService()
                , recommendationService()
        );
    }
}
