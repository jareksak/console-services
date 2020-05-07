package artur.sanko;

import artur.sanko.menu.MainMenu;

import java.io.Console;

import static artur.sanko.menu.MainMenu.ASTRO;
import static artur.sanko.menu.MainMenu.EXIT;
import static artur.sanko.menu.MainMenu.STAT;
import static artur.sanko.menu.MainMenu.WEATHER;

public class ConsoleServices {

    public static void main(String[] args) {

        Console console = getConsonle();

        MainMenu mainMenu = new MainMenu();
        console.writer().print(mainMenu.buildMenu());

        String menuItemKey = console.readLine("\n>>> ");
        while (true) {

            if (EXIT.equals(menuItemKey)) {
                break;
            }

            String menuItemDescription = mainMenu.getDescription(menuItemKey);
            switch (menuItemKey) {

                case ASTRO:
                    console.writer().print(menuItemDescription);
                    break;

                case WEATHER:
                    console.writer().print(menuItemDescription);
                    break;

                case STAT:
                    console.writer().print(menuItemDescription);
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
}
