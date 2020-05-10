package artur.sanko.enumeration;

import java.util.stream.Stream;

public enum MainMenuItem {

    ASTRO("Астрологический прогноз"),
    WEATHER("Прогноз погоды"),
    STAT("Статистика"),
    EXIT("Выход");

    private String description;

    private MainMenuItem(String description) {

        this.description = description;
    }

    public String getDescription() {

        return description;
    }

    public static String buildMenu() {

        StringBuilder builder = new StringBuilder();
        builder.append("Главное меню:\n");
        Stream.of(values()).forEach(v -> builder.append(v.getDescription() + " (" + v.name() + ")\n"));

        return builder.toString();
    }
}
