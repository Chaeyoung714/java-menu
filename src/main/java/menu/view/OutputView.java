package menu.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import menu.model.recommendation.Coach;
import menu.model.recommendation.RecommendedCategories;
import menu.model.recommendation.RecommendedMenus;
import menu.model.lunch.Menu;
import menu.model.lunch.Weekday;

public class OutputView {
    private static final String JOINING_DELIMITER = " | ";

    public void printRecommendationResult(RecommendedCategories recommendedCategories, List<Coach> coaches,
                                          Map<Coach, RecommendedMenus> recommendationResult) {
        printStartLine();
        printWeekdays();
        printRecommendedCategories(recommendedCategories);
        printRecommendedMenus(coaches, recommendationResult);
        printEndLine();
    }

    private void printStartLine() {
        System.out.println(System.lineSeparator() + "메뉴 추천 결과입니다.");
    }

    private void printWeekdays() {
        List<String> weekdaysInfo = new ArrayList<>(Arrays.asList("구분"));
        weekdaysInfo.addAll(Weekday.getAllInOrder().stream()
                .map(w -> w.getName()).collect(Collectors.toList()));
        System.out.println(joinAndFormatToString(weekdaysInfo));
    }

    private void printRecommendedCategories(RecommendedCategories recommendedCategories) {
        List<String> categoriesInfo = new ArrayList<>(Arrays.asList("카테고리"));
        categoriesInfo.addAll(Weekday.getAllInOrder().stream()
                .map(w -> recommendedCategories.findByWeekday(w).getName()).collect(Collectors.toList()));
        System.out.println(joinAndFormatToString(categoriesInfo));
    }

    private void printRecommendedMenus(List<Coach> coachesInOrder, Map<Coach, RecommendedMenus> recommendationResult) {
        for (Coach coach : coachesInOrder) {
            List<String> recommendationInfo = new ArrayList<>(Arrays.asList(coach.getName()));
            Map<Weekday, Menu> recommendedMenus = recommendationResult.get(coach).getMenus();
            recommendationInfo.addAll(Weekday.getAllInOrder().stream()
                    .map(w -> recommendedMenus.get(w).getName()).collect(Collectors.toList()));
            System.out.println(joinAndFormatToString(recommendationInfo));
        }
    }

    private void printEndLine() {
        System.out.println(System.lineSeparator() + "추천을 완료했습니다.");
    }

    private String joinAndFormatToString(List<String> messages) {
        return String.format("[ %s ]", String.join(JOINING_DELIMITER, messages));
    }
}
