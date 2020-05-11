package artur.sanko;

import artur.sanko.enumeration.MainMenuItem;
import artur.sanko.enumeration.PredictionPeriod;
import artur.sanko.service.AstroService;
import artur.sanko.service.BillingService;
import artur.sanko.service.UserInputService;
import artur.sanko.service.WeatherService;

import java.io.Console;
import java.time.LocalDate;
import java.util.Optional;

import static artur.sanko.enumeration.MainMenuItem.EXIT;

public class ConsoleServices {

    private static UserInputService inputService = UserInputService.getInstance();
    private static AstroService astroService = AstroService.getInstance();
    private static WeatherService weatherService = WeatherService.getInstance();
    private static BillingService billingService = BillingService.getInstance();

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
                    billingService.addBill(MainMenuItem.ASTRO.name());
                    LocalDate birthDate = inputService.readDate(console, "Дата рождения");
                    LocalDate startDate = inputService.readDate(console, "Дата предсказания");
                    PredictionPeriod predictionPeriod = inputService.readPredictionPeriod(console, "Период");
                    console.writer().print(astroService.getPrediction(birthDate, startDate, predictionPeriod));
                    break;

                case WEATHER:
                    billingService.addBill(MainMenuItem.WEATHER.name());
                    Optional<LocalDate> predictionDateOpt = inputService.readOptionalDate(console, "Прогноз на дату");
                    Optional<PredictionPeriod> predictionPeriodOpt = inputService.readOptionalPredictionPeriod(console,
                            "Прогноз на период");
                    console.writer().print(weatherService.getPrediction(predictionDateOpt, predictionPeriodOpt));
                    break;

                case STAT:
                    console.writer().print(billingService.buildBillingStatistic());
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
