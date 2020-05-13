package artur.sanko.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class WeatherPredictionCsvLoader implements WeatherPredictionLoader {

    private static final String DELIMITER = ";";
    public static final String WEATHER_CSV = "weather.csv";

    private Set<String> predictions = new LinkedHashSet<>();

    private static WeatherPredictionCsvLoader weatherPredictionCsvLoader;

    private WeatherPredictionCsvLoader() {
    }

    public static WeatherPredictionCsvLoader getInstance() {

        if (weatherPredictionCsvLoader == null) {

            weatherPredictionCsvLoader = new WeatherPredictionCsvLoader();
        }

        return weatherPredictionCsvLoader;
    }

    @Override
    public Set<String> load(String path) {

        if (!predictions.isEmpty()) {

            return predictions;
        }

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] columns = line.split(DELIMITER);
                if (columns.length > 0 && !columns[0].isEmpty()) {

                    predictions.add(columns[0]);
                }
            }

        } catch (IOException e) {

            System.out.println(String.format("Ошибка чтения файла '%s': %s ", path, e.getMessage()));
        }

        return predictions;
    }
}
