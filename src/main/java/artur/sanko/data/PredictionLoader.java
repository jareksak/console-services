package artur.sanko.data;

import java.util.Set;

public interface PredictionLoader {

    Set<String> load(String path);
}
