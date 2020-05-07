package artur.sanko;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class ConsoleServices {

    public static void main(String[] args) {

        Console console = getConsonle();

        console.writer().print(buildMainMenu());

        String menuItemKey = console.readLine("\n>>> ");
        while (true) {

            if (KEY_4.equals(menuItemKey)) {
                break;
            }
            switch (menuItemKey) {

                case KEY_1:
                    console.writer().print(mainMenu.get(KEY_1));
                    break;

                case KEY_2:
                    console.writer().print(mainMenu.get(KEY_2));
                    break;

                case KEY_3:
                    console.writer().print(mainMenu.get(KEY_3));
                    break;

                default:
                    console.printf("Неизвестный ключ '%s'\n", menuItemKey);
            }

            menuItemKey = console.readLine("\n>>> ");
        }
    }

    private static Console getConsonle() {

        Console console = System.console();
        if (console == null) {

            throw new IllegalStateException("Программа запущена не из консоли.");
        }

        return console;
    }

    private static String buildMainMenu() {

        StringBuilder builder = new StringBuilder();
        builder.append("Главное меню:\n");
        mainMenu.forEach((key, description) -> builder.append(description + " (" + key + ")\n"));

        return builder.toString();
    }

    private static final String KEY_1 = "1";
    private static final String KEY_2 = "2";
    private static final String KEY_3 = "3";
    private static final String KEY_4 = "4";
    private static Map<String, String> mainMenu = new HashMap();

    static {
        mainMenu.put(KEY_1, "Астрологический прогноз");
        mainMenu.put(KEY_2, "Прогноз погоды");
        mainMenu.put(KEY_3, "Статистика");
        mainMenu.put(KEY_4, "Выход");
    }
}
