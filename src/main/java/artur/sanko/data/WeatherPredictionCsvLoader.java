package artur.sanko.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class WeatherPredictionCsvLoader implements WeatherPredictionLoader {

    private static final String DELIMITER = ";";
    private static final String FILE = "weather.csv";

    private static WeatherPredictionCsvLoader weatherPredictionCsvLoader;

    private WeatherPredictionCsvLoader() {
    }

    public static WeatherPredictionCsvLoader getInstance() {

        if (weatherPredictionCsvLoader == null) {

            weatherPredictionCsvLoader = new WeatherPredictionCsvLoader();
        }

        return weatherPredictionCsvLoader;
    }

    // сохраняет порядок добавления и содержит только уникальные значения
    private Set<String> predictions = new LinkedHashSet<>();

    @Override
    public Set<String> load() {

        if (!predictions.isEmpty()) {

            return predictions;
        }

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] columns = line.split(DELIMITER);
                if (columns.length > 0 && !columns[0].isEmpty()) {

                    predictions.add(columns[0]);
                }
            }

        } catch (IOException e) {

            System.out.println(String.format("Ошибка чтения файла '%s': %s ", FILE, e.getMessage()));
        }

        return predictions;
    }

    public static void main(String[] args) {

        WeatherPredictionLoader loader = new WeatherPredictionCsvLoader();
        loader.load();
    }
}
