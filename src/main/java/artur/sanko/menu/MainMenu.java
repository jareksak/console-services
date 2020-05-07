package artur.sanko.menu;

import java.util.HashMap;
import java.util.Map;

public class MainMenu {

    public static final String ASTRO = "1";
    public static final String WEATHER = "2";
    public static final String STAT = "3";
    public static final String EXIT = "4";

    protected Map<String, String> menuMap = new HashMap<>();

    public MainMenu() {

        menuMap.put(ASTRO, "Астрологический прогноз");
        menuMap.put(WEATHER, "Прогноз погоды");
        menuMap.put(STAT, "Статистика");
        menuMap.put(EXIT, "Выход");
    }

    public String buildMenu() {

        StringBuilder builder = new StringBuilder();
        builder.append("Главное меню:\n");
        menuMap.forEach((key, description) -> builder.append(description + " (" + key + ")\n"));

        return builder.toString();
    }

    public String getDescription(String key) {

        return menuMap.get(key);
    }
}
