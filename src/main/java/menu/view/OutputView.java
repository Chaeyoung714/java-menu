package menu.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import menu.model.Coach;
import menu.model.Weekday;
import menu.model.WeeklyCategories;
import menu.model.WeeklyMenus;
import menu.model.WeeklyRecommendation;

public class OutputView {

    public void printServiceStartLine() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public void printRecommendations(WeeklyRecommendation recommendations, WeeklyCategories categories) {
        printStartLine();
        printWeekdays();
        printWeeklyCategories(categories);
        printWeeklyRecommendations(recommendations);
        printEndLine();
    }

    private void printStartLine() {
        System.out.println(System.lineSeparator() + "메뉴 추천 결과입니다.");
    }

    private void printWeekdays() {
        List<String> weekdaysPhrase = new ArrayList<>(Arrays.asList("구분"));
        weekdaysPhrase.addAll(Weekday.getInOrder().stream()
                .map(w -> w.getName())
                .collect(Collectors.toList()));
        System.out.println(String.format("[ %s ]", String.join(" | ", weekdaysPhrase)));
    }

    private void printWeeklyCategories(WeeklyCategories categories) {
        List<String> categoriesPhrase = new ArrayList<>(Arrays.asList("카테고리"));
        categoriesPhrase.addAll(Weekday.getInOrder().stream()
                .map(w -> categories.getCategoryNameOf(w))
                .collect(Collectors.toList()));
        System.out.println(String.format("[ %s ]", String.join(" | ", categoriesPhrase)));
    }

    private void printWeeklyRecommendations(WeeklyRecommendation recommendations) {
        for (Coach coach : recommendations.getRecommendations().keySet()) {
            WeeklyMenus weeklyMenus = recommendations.findByCoach(coach);
            List<String> recommendationPhrase = new ArrayList<>(Arrays.asList(coach.getName()));
            recommendationPhrase.addAll(Weekday.getInOrder().stream()
                    .map(w -> weeklyMenus.findByWeekday(w).getName())
                    .collect(Collectors.toList()));
            System.out.println(String.format("[ %s ]", String.join(" | ", recommendationPhrase)));
        }
    }

    private void printEndLine() {
        System.out.println(System.lineSeparator() + "추천을 완료했습니다.");
    }
}
