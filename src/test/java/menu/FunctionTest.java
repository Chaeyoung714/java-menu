package menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import menu.model.lunch.Category;
import menu.model.lunch.Weekday;
import menu.model.recommendation.Coach;
import menu.model.recommendation.RecommendedCategories;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;
import menu.repository.RecommendationRepository;
import menu.service.CategoryService;
import menu.service.CoachService;
import menu.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FunctionTest {
    private final MenuRepository menuRepository = new MenuRepository();

    private CoachRepository coachRepository;
    private RecommendationRepository recommendationRepository;
    private CoachService coachService;
    private MenuService menuService;
    private CategoryService categoryService;


    @BeforeEach
    void setUp() {
        coachRepository = new CoachRepository();
        recommendationRepository = new RecommendationRepository();
        coachService = new CoachService(coachRepository, menuRepository);
        menuService = new MenuService(coachRepository, menuRepository, recommendationRepository);
        categoryService = new CategoryService();
        coachService.registerCoaches(List.of(
                "포비", "로로"
        ));
    }

    @Test
    void 메뉴_받아오기_테스트() {
        assertThat(menuRepository.findAll().size()).isEqualTo(45);
    }

    @Test
    void 못먹는메뉴_저장_테스트() {
        Coach poby = coachRepository.findByName("포비");

        coachService.registerForbiddenMenuName(poby, List.of("우동", "스시"));

        assertThat(poby.getForbiddenMenus().size()).isEqualTo(2);
        assertThat(poby.getForbiddenMenus().get(0)).isEqualTo(menuRepository.findByName("우동"));
    }

    @Test
    void 카테고리_및_메뉴_추천_테스트() {
        Coach poby = coachRepository.findByName("포비");
        Coach roro = coachRepository.findByName("로로");
        RecommendedCategories recommendedCategories = categoryService.recommendCategories();

        menuService.recommendMenus(recommendedCategories);

        assertThat(recommendationRepository.findAll().size()).isEqualTo(2);
        assertThat(recommendationRepository.findAll().get(poby).getMenus().size()).isEqualTo(5);
        assertThat(recommendationRepository.findAll().get(roro).getMenus().size()).isEqualTo(5);
    }

}
