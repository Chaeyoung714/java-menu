package menu.service;

import static menu.exception.ExceptionMessages.MENU_NOT_EXIST;
import static menu.exception.ExceptionMessages.RECOMMEND_SYSTEM_FAIL;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import menu.exception.ExceptionMessages;
import menu.exception.customExceptions.DuplicatedMenuException;
import menu.exception.customExceptions.ForbiddenMenuException;
import menu.exception.customExceptions.MenuNotExistsException;
import menu.model.recommendation.Coach;
import menu.model.recommendation.RecommendedCategories;
import menu.model.recommendation.RecommendedMenus;
import menu.model.lunch.Category;
import menu.model.lunch.Menu;
import menu.model.lunch.Weekday;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;
import menu.repository.RecommendationRepository;

public class MenuService {
    private final CoachRepository coachRepository;
    private final MenuRepository menuRepository;
    private final RecommendationRepository recommendationRepository;

    public MenuService(CoachRepository coachRepository, MenuRepository menuRepository,
                       RecommendationRepository recommendationRepository) {
        this.coachRepository = coachRepository;
        this.menuRepository = menuRepository;
        this.recommendationRepository = recommendationRepository;
    }

    public Map<Coach, RecommendedMenus> recommendMenus(RecommendedCategories recommendedCategories) {
        Map<Coach, RecommendedMenus> recommendationResult = initializeResult();
        for (Weekday weekday : Weekday.getAllInOrder()) {
            for (Coach coach : coachRepository.findAll()) {
                RecommendedMenus recommendationMiddleResult = recommendationResult.get(coach);
                recommendMenuPerDayPerCoach(weekday, coach, recommendationMiddleResult,
                        recommendedCategories.findByWeekday(weekday));
            }
        }
        for (Coach coach : coachRepository.findAll()) {
            recommendationRepository.save(coach, recommendationResult.get(coach));
        }
        return recommendationRepository.findAll();
    }

    private Map<Coach, RecommendedMenus> initializeResult() {
        Map<Coach, RecommendedMenus> recommendationResult = new HashMap<>();
        for (Coach coach : coachRepository.findAll()) {
            recommendationResult.put(coach, new RecommendedMenus());
        }
        return recommendationResult;
    }

    private void recommendMenuPerDayPerCoach(Weekday weekday, Coach coach, RecommendedMenus recommendedMenus,
                                             Category category) {
        while (true) {
            try {
                Menu randomMenu = menuRepository.findByName(
                        Randoms.shuffle(menuRepository.findNamesByCategory(category)).get(0));
                validateMenuAvailability(randomMenu, coach, recommendedMenus);
                recommendedMenus.update(weekday, randomMenu);
                return;
            } catch (ForbiddenMenuException | DuplicatedMenuException exception) {
            } catch (OutOfMemoryError error) {
                throw new IllegalStateException(RECOMMEND_SYSTEM_FAIL.getMessage());
            }
        }
    }

    private void validateMenuAvailability(Menu randomMenu, Coach coach, RecommendedMenus recommendedMenus) {
        if (coach.containsForbiddenMenu(randomMenu)) {
            throw new ForbiddenMenuException();
        }
        if (recommendedMenus.containsMenu(randomMenu)) {
            throw new DuplicatedMenuException();
        }
    }
}
