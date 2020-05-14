package artur.sanko.service;

import artur.sanko.data.PredictionLoader;
import artur.sanko.data.PredictionSqliteLoader;
import artur.sanko.enumeration.PredictionPeriod;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static artur.sanko.data.PredictionSqliteLoader.IN_MEMORY_DATABASE;
import static artur.sanko.helper.CollectionsHelper.getRandomElement;

public class AstroService {

    private PredictionLoader databaseLoader = PredictionSqliteLoader.getInstance();

    private static AstroService astroService;

    private AstroService() {
    }

    public static AstroService getInstance() {

        if (astroService == null) {

            astroService = new AstroService();
        }

        return astroService;
    }

    public String getRandomPrediction() {

        Set<String> predictions = new LinkedHashSet<>();
        predictions.addAll(databaseLoader.load(IN_MEMORY_DATABASE));

        return getRandomElement(predictions).orElse("");
    }

    public String getPrediction(LocalDate birthDate, Optional<LocalDate> predictionDate,
                                Optional<PredictionPeriod> predictionPeriod) {

        String prediction = "\n";

        if (predictionDate.isPresent() || predictionPeriod.isPresent()) {

            prediction = getRandomPrediction() + "\n";
        }

        return prediction;
    }
}
