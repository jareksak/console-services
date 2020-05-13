package artur.sanko.data;

import java.util.Set;

public interface WeatherPredictionLoader {

    Set<String> load(String path);
}
