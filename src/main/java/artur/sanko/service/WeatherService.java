package artur.sanko.service;

import artur.sanko.enumeration.PredictionPeriod;

import java.time.LocalDate;
import java.util.Optional;

public class WeatherService {

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

        //TODO
        return date.toString();
    }

    public String getPrediction(PredictionPeriod period) {

        //TODO
        return period.name();
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
