package artur.sanko.data;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class PredictionJsonLoader implements PredictionLoader {

    public static final String WEATHER_JSON = "weather.json";

    private Set<String> predictions = new LinkedHashSet<>();

    private static PredictionJsonLoader weatherPredictionJsonLoader;

    private PredictionJsonLoader() {
    }

    public static PredictionJsonLoader getInstance() {

        if (weatherPredictionJsonLoader == null) {

            weatherPredictionJsonLoader = new PredictionJsonLoader();
        }

        return weatherPredictionJsonLoader;
    }

    @Override
    public Set<String> load(String path) {

        if (!predictions.isEmpty()) {

            return predictions;
        }

        JSONParser parser = new JSONParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(inputStream));
            Iterator<String> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {

                predictions.add(iterator.next());
            }

        } catch (IOException | ParseException e) {

            System.out.println(String.format("Ошибка чтения файла '%s': %s ", path, e.getMessage()));
        }

        return predictions;
    }
}
