package artur.sanko.enumeration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PredictionPeriod {

    DAY, WEEK, MONTH;

    public static String getNames() {

        return Stream.of(values()).map(Enum::name).collect(Collectors.joining("/"));
    }
}
