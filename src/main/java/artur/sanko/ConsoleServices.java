package artur.sanko;

import artur.sanko.enumeration.MainMenuItem;
import artur.sanko.enumeration.PredictionPeriod;
import artur.sanko.service.AstroService;
import artur.sanko.service.UserInputService;

import java.io.Console;
import java.time.LocalDate;

import static artur.sanko.enumeration.MainMenuItem.EXIT;

public class ConsoleServices {

    private static UserInputService inputService = UserInputService.getInstance();
    private static AstroService astroService = AstroService.getInstance();

    public static void main(String[] args) {

        Console console = getConsonle();
        console.writer().print(MainMenuItem.buildMenu());

        while (true) {

            MainMenuItem mainMenuItem = inputService.readMainMenuItem(console, "\n>>> ");
            if (EXIT.equals(mainMenuItem)) {
                break;
            }

            switch (mainMenuItem) {

                case ASTRO:

                    LocalDate birthDate = inputService.readDate(console, "Дата рождения");
                    LocalDate startDate = inputService.readDate(console, "Дата предсказания");
                    PredictionPeriod predictionPeriod = inputService.readPredictionPeriod(console, "Период");
                    console.writer().print(astroService.getPrediction(birthDate, startDate, predictionPeriod));
                    break;

                case WEATHER:
                    console.writer().print(mainMenuItem.getDescription());
                    break;

                case STAT:
                    console.writer().print(mainMenuItem.getDescription());
                    break;

                default:
            }
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
