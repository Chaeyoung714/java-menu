package menu.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import menu.exception.MenuNotExistsException;
import menu.model.lunch.Category;
import menu.model.lunch.Menu;
import menu.util.MenuScanner;

public class MenuRepository {
    private final List<Menu> menus;

    public MenuRepository() {
        this.menus = registerMenus();
    }

    private List<Menu> registerMenus() {
        List<Menu> menus = new ArrayList<>();
        menus.addAll(MenuScanner.scanMenu("규동, 우동, 미소시루, 스시, 가츠동, 오니기리, 하이라이스, 라멘, 오코노미야끼", Category.JAPANESE));
        menus.addAll(MenuScanner.scanMenu("김밥, 김치찌개, 쌈밥, 된장찌개, 비빔밥, 칼국수, 불고기, 떡볶이, 제육볶음", Category.KOREAN));
        menus.addAll(MenuScanner.scanMenu("깐풍기, 볶음면, 동파육, 짜장면, 짬뽕, 마파두부, 탕수육, 토마토 달걀볶음, 고추잡채", Category.CHINESE));
        menus.addAll(MenuScanner.scanMenu("팟타이, 카오 팟, 나시고렝, 파인애플 볶음밥, 쌀국수, 똠얌꿍, 반미, 월남쌈, 분짜", Category.ASIAN));
        menus.addAll(MenuScanner.scanMenu("라자냐, 그라탱, 뇨끼, 끼슈, 프렌치 토스트, 바게트, 스파게티, 피자, 파니니", Category.WESTERN));
        return menus;
    }

    public List<Menu> findAll() {
        return Collections.unmodifiableList(menus);
    }

    public Menu findByName(String name) {
        return menus.stream()
                .filter(m -> m.getName().equals(name))
                .findFirst()
                .orElseThrow(MenuNotExistsException::new);
    }

    public List<String> findNamesByCategory(Category category) {
        return menus.stream()
                .filter(m -> m.getCategory().equals(category))
                .map(m -> m.getName())
                .collect(Collectors.toList());
    }
}
