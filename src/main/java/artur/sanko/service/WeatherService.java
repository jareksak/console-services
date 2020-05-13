package artur.sanko.service;

import artur.sanko.data.WeatherPredictionCsvLoader;
import artur.sanko.data.WeatherPredictionJsonLoader;
import artur.sanko.data.WeatherPredictionLoader;
import artur.sanko.enumeration.PredictionPeriod;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static artur.sanko.data.WeatherPredictionCsvLoader.WEATHER_CSV;
import static artur.sanko.data.WeatherPredictionJsonLoader.WEATHER_JSON;
import static artur.sanko.helper.CollectionsHelper.getRandomElement;

public class WeatherService {

    private WeatherPredictionLoader csvLoader = WeatherPredictionCsvLoader.getInstance();
    private WeatherPredictionJsonLoader jsonLoader = WeatherPredictionJsonLoader.getInstance();
    private Map<LocalDate, String> predictionsMap = new HashMap<>();

    private static WeatherService weatherService;

    private WeatherService() {
    }

    public static WeatherService getInstance() {

        if (weatherService == null) {

            weatherService = new WeatherService();
        }

        return weatherService;
    }

    public String getPrediction(LocalDate date) {

        Set<String> predictions = new LinkedHashSet<>();
        predictions.addAll(csvLoader.load(WEATHER_CSV));
        predictions.addAll(jsonLoader.load(WEATHER_JSON));
        String randomPrediction = getRandomElement(predictions).orElse("");
        predictionsMap.putIfAbsent(date, String.format("%s: %s\n", date.toString(), randomPrediction));

        return predictionsMap.get(date);
    }

    public String getPrediction(PredictionPeriod period) {

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate;
        switch (period) {

            case WEEK:
                endDate = startDate.plusWeeks(1L);
                break;

            case MONTH:
                endDate = startDate.plusMonths(1L);
                break;

            default:
        }

        return getPrediction(startDate, endDate);
    }

    public String getPrediction(LocalDate startDate, LocalDate endDate) {

        StringBuilder builder = new StringBuilder();
        if (endDate.isAfter(startDate) || startDate.equals(endDate)) {

            do {

                builder.append(getPrediction(startDate));
                startDate = startDate.plusDays(1L);

            } while (startDate.isBefore(endDate));
        }

        return builder.toString();
    }

    public String getPrediction(Optional<LocalDate> dateOpt, Optional<PredictionPeriod> periodOpt) {

        String prediction = "";
        if (dateOpt.isPresent()) {

            prediction = getPrediction(dateOpt.get());

        } else if (periodOpt.isPresent()) {

            prediction = getPrediction(periodOpt.get());
        }

        return prediction;
    }

}
