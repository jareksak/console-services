package artur.sanko.service;

import artur.sanko.enumeration.PredictionPeriod;

import java.time.LocalDate;

public class AstroService {

    private static AstroService astroService;

    private AstroService() {
    }

    public static AstroService getInstance() {

        if (astroService == null) {

            astroService = new AstroService();
        }

        return astroService;
    }

    public String getPrediction(LocalDate birthDate, LocalDate predictionDate, PredictionPeriod predictionPeriod) {

        //TODO: get random prediction
        return String.join(" | ", birthDate.toString(), predictionDate.toString(), predictionPeriod.toString());
    }
}
