package menu.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import menu.exception.DuplicatedCategoryException;
import menu.model.lunch.Category;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;

public class RecommendationService {
    private final CoachRepository coachRepository;
    private final MenuRepository menuRepository;

    public RecommendationService(CoachRepository coachRepository, MenuRepository menuRepository) {
        this.coachRepository = coachRepository;
        this.menuRepository = menuRepository;
    }


    public List<Category> recommendCategories() {
        List<Category> categories = new ArrayList<>(5);
        while (categories.size() < 5) {
            try {
                int number = Randoms.pickNumberInRange(1, 5);
                Category randomCategory = Category.findByNumber(number);
                validateAvailability(randomCategory, categories);
                categories.add(randomCategory);
            } catch (DuplicatedCategoryException e) {
            } catch (OutOfMemoryError error) {
                throw new IllegalStateException("[ERROR][SYSTEM] 추천에 실패했습니다.");
            }
        }
        return categories;
    }

    private void validateAvailability(Category randomCategory, List<Category> categories) {
        long duplicationCount = categories.stream()
                .filter(c -> c.equals(randomCategory))
                .count();
        if (duplicationCount >= 2) {
            throw new DuplicatedCategoryException();
        }
    }

    public void recommendMenus() {
    }
}
