package menu;


import menu.controller.MenuController;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;
import menu.service.CoachService;
import menu.view.InputHandler;
import menu.view.InputView;
import menu.view.OutputView;

public class DependencyConfig {
    private final MenuRepository menuRepository = new MenuRepository();
    private final CoachRepository coachRepository = new CoachRepository();

    private CoachService coachService() {
        return new CoachService(coachRepository);
    }

    public MenuController menuController() {
        return new MenuController(
                new InputHandler(new InputView())
                , new OutputView()
                , coachService()
        );
    }
}
