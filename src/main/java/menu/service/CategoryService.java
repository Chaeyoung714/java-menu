package menu.service;

import static menu.exception.ExceptionMessages.RECOMMEND_SYSTEM_FAIL;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import menu.exception.customExceptions.DuplicatedCategoryException;
import menu.model.lunch.Category;
import menu.model.lunch.Weekday;
import menu.model.recommendation.RecommendedCategories;

public class CategoryService {

    private static final int CATEGORY_DUPLICATION_UPPER_LIMIT = 2;

    public RecommendedCategories recommendCategories() {
        List<Category> categories = new ArrayList<>();
        while (categories.size() < Weekday.COUNT) {
            try {
                int number = Randoms.pickNumberInRange(Category.START_NUMBER, Category.LAST_NUMBER);
                Category randomCategory = Category.findByNumber(number);
                validateCategoryAvailability(randomCategory, categories);
                categories.add(randomCategory);
            } catch (DuplicatedCategoryException e) {
            } catch (OutOfMemoryError error) {
                throw new IllegalStateException(RECOMMEND_SYSTEM_FAIL.getMessage());
            }
        }
        return RecommendedCategories.of(categories);
    }

    private void validateCategoryAvailability(Category randomCategory, List<Category> categories) {
        long duplicationCount = categories.stream()
                .filter(c -> c.equals(randomCategory))
                .count();
        if (duplicationCount >= CATEGORY_DUPLICATION_UPPER_LIMIT) {
            throw new DuplicatedCategoryException();
        }
    }
}
