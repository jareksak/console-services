package artur.sanko.service;

import artur.sanko.enumeration.MainMenuItem;
import artur.sanko.enumeration.PredictionPeriod;

import java.io.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class UserInputService {

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    private static UserInputService userInputService;

    private UserInputService() {
    }

    public static UserInputService getInstance() {

        if (userInputService == null) {

            userInputService = new UserInputService();
        }

        return userInputService;
    }

    public Optional<LocalDate> readOptionalDate(Console console, String description) {

        String dateStr = console.readLine("%s, (%s): ", description, DATE_PATTERN);

        return dateStr.isEmpty() ? Optional.empty() : toLocalDate(dateStr);
    }

    public LocalDate readDate(Console console, String description) {

        while (true) {

            Optional<LocalDate> localDateOpt = readOptionalDate(console, description);
            if (localDateOpt.isPresent()) {

                return localDateOpt.get();
            }
        }
    }

    public Optional<LocalDate> toLocalDate(String dateStr) {

        LocalDate localDate = null;
        try {

            localDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_PATTERN));

        } catch (DateTimeParseException e) {

            System.out.println(String.format("Oшибка преобразования в дату: %s", e.getMessage()));
        }

        return Optional.ofNullable(localDate);
    }

    public Optional<PredictionPeriod> readOptionalPredictionPeriod(Console console, String description) {

        String periodStr = console.readLine("%s, (%s): ", description, PredictionPeriod.getNames());

        return toPredictionPeriod(periodStr);
    }

    public PredictionPeriod readPredictionPeriod(Console console, String description) {

        while (true) {

            Optional<PredictionPeriod> predictionPeriodOpt = readOptionalPredictionPeriod(console, description);
            if (predictionPeriodOpt.isPresent()) {

                return predictionPeriodOpt.get();
            }
        }
    }

    public Optional<PredictionPeriod> toPredictionPeriod(String periodStr) {

        PredictionPeriod predictionPeriod = null;
        if (!periodStr.isEmpty()) {
            try {

                predictionPeriod = PredictionPeriod.valueOf(periodStr);

            } catch (IllegalArgumentException e) {

                System.out.println(String.format("Неизвестный период: '%s'", periodStr));
            }
        }

        return Optional.ofNullable(predictionPeriod);
    }

    public MainMenuItem readMainMenuItem(Console console, String description) {

        while (true) {

            String mainMenuItemStr = console.readLine(description);
            Optional<MainMenuItem> mainMenuItemOpt = toMainMenuItem(mainMenuItemStr);
            if (mainMenuItemOpt.isPresent()) {

                return mainMenuItemOpt.get();
            }
        }
    }

    public Optional<MainMenuItem> toMainMenuItem(String menuItemStr) {

        MainMenuItem mainMenuItem = null;
        try {

            mainMenuItem = MainMenuItem.valueOf(menuItemStr);

        } catch (IllegalArgumentException e) {

            System.out.println(String.format("Heизвестый элемент меню: '%s'", menuItemStr));
        }

        return Optional.ofNullable(mainMenuItem);
    }
}
