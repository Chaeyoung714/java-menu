package menu.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import menu.model.Category;
import menu.model.Menu;
import menu.util.InfoScanner;

public class MenuRepository {
    private final List<Menu> menus;

    public MenuRepository() {
        this.menus = register();
    }

    private List<Menu> register() {
        List<Menu> menus = new ArrayList<>();
        menus.addAll(registerMenu(Category.JAPANESE, "규동, 우동, 미소시루, 스시, 가츠동, 오니기리, 하이라이스, 라멘, 오코노미야끼"));
        menus.addAll(registerMenu(Category.KOREAN, "김밥, 김치찌개, 쌈밥, 된장찌개, 비빔밥, 칼국수, 불고기, 떡볶이, 제육볶음"));
        menus.addAll(registerMenu(Category.CHINESE, "깐풍기, 볶음면, 동파육, 짜장면, 짬뽕, 마파두부, 탕수육, 토마토 달걀볶음, 고추잡채"));
        menus.addAll(registerMenu(Category.ASIAN, "팟타이, 카오 팟, 나시고렝, 파인애플 볶음밥, 쌀국수, 똠얌꿍, 반미, 월남쌈, 분짜"));
        menus.addAll(registerMenu(Category.WESTERN, "라자냐, 그라탱, 뇨끼, 끼슈, 프렌치 토스트, 바게트, 스파게티, 피자, 파니니"));
        return menus;
    }

    private List<Menu> registerMenu(Category category, String menuInfo) {
        List<String> parsedMenu = InfoScanner.readMenu(menuInfo);
        return parsedMenu.stream()
                .map(p -> new Menu(p, category))
                .collect(Collectors.toList());
    }

    public List<Menu> findAll() {
        return Collections.unmodifiableList(menus);
    }

    public Menu findByName(String name) {
        return menus.stream()
                .filter(m -> m.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
