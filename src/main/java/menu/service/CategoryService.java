package menu.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import menu.exception.customExceptions.DuplicatedCategoryException;
import menu.model.lunch.Category;
import menu.model.recommendation.RecommendedCategories;

public class CategoryService {

    public RecommendedCategories recommendCategories() {
        List<Category> categories = new ArrayList<>(5);
        while (categories.size() < 5) {
            try {
                int number = Randoms.pickNumberInRange(1, 5);
                Category randomCategory = Category.findByNumber(number);
                validateCategoryAvailability(randomCategory, categories);
                categories.add(randomCategory);
            } catch (DuplicatedCategoryException e) {
            } catch (OutOfMemoryError error) {
                throw new IllegalStateException("[ERROR][SYSTEM] 추천에 실패했습니다.");
            }
        }
        return RecommendedCategories.of(categories);
    }

    private void validateCategoryAvailability(Category randomCategory, List<Category> categories) {
        long duplicationCount = categories.stream()
                .filter(c -> c.equals(randomCategory))
                .count();
        if (duplicationCount >= 2) {
            throw new DuplicatedCategoryException();
        }
    }

}
