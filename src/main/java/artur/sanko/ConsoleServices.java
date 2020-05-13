package artur.sanko;

import artur.sanko.enumeration.MainMenuItem;
import artur.sanko.enumeration.PredictionPeriod;
import artur.sanko.service.AstroService;
import artur.sanko.service.BillingService;
import artur.sanko.service.UserInputService;
import artur.sanko.service.WeatherService;

import java.time.LocalDate;
import java.util.Optional;

import static artur.sanko.enumeration.MainMenuItem.EXIT;
import static artur.sanko.enumeration.MainMenuItem.buildMenu;

public class ConsoleServices {

    private static UserInputService inputService = UserInputService.getInstance();
    private static AstroService astroService = AstroService.getInstance();
    private static WeatherService weatherService = WeatherService.getInstance();
    private static BillingService billingService = BillingService.getInstance();

    public static void main(String[] args) {

        System.out.print(buildMenu());
        while (true) {

            MainMenuItem mainMenuItem = inputService.readMainMenuItem("\n>>> ");
            if (EXIT.equals(mainMenuItem)) {
                break;
            }

            switch (mainMenuItem) {

                case ASTRO:
                    billingService.addBill(MainMenuItem.ASTRO.name());
                    LocalDate birthDate = inputService.readDate("Дата рождения");
                    LocalDate startDate = inputService.readDate("Дата предсказания");
                    PredictionPeriod predictionPeriod = inputService.readPredictionPeriod("Период");
                    System.out.print(astroService.getPrediction(birthDate, startDate, predictionPeriod));
                    break;

                case WEATHER:
                    billingService.addBill(MainMenuItem.WEATHER.name());
                    Optional<LocalDate> predictionDateOpt = inputService.readOptionalDate("Прогноз на дату");
                    Optional<PredictionPeriod> predictionPeriodOpt = inputService.readOptionalPredictionPeriod(
                            "Прогноз на период");
                    System.out.print(weatherService.getPrediction(predictionDateOpt, predictionPeriodOpt));
                    break;

                case STAT:
                    System.out.print(billingService.buildBillingStatistic());
                    break;

                default:
            }
        }
    }
}
