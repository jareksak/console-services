package artur.sanko.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

public class PredictionSqliteLoader implements PredictionLoader {

    public static final String IN_MEMORY_DATABASE = ":memory:";

    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS astrology (\n" +
            "   id integer PRIMARY KEY,\n" +
            "   prediction text NOT NULL\n" +
            ");";

    private static final String INSERT_SQL = "INSERT INTO astrology (prediction) VALUES(?)";

    private static final String SELECT_SQL = "SELECT prediction FROM astrology";

    private Set<String> predictions = new LinkedHashSet<>();

    private static PredictionSqliteLoader predictionSqliteLoader;

    private PredictionSqliteLoader() {
    }

    public static PredictionSqliteLoader getInstance() {

        if (predictionSqliteLoader == null) {

            predictionSqliteLoader = new PredictionSqliteLoader();
        }

        return predictionSqliteLoader;
    }

    public Set<String> load(String path) {

        if (!predictions.isEmpty()) {

            return predictions;
        }

        try (Connection conn = connect(path)) {

            createTable(conn);
            insertRows(conn);
            loadPredictions(conn);

        } catch (SQLException e) {

            System.out.println(String.format("Oшибка закрытия соединения с базой данных: %s", e.getMessage()));
        }

        return predictions;
    }

    protected void loadPredictions(Connection conn) {

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_SQL)) {

            while (rs.next()) {

                predictions.add(rs.getString("prediction"));
            }

        } catch (SQLException e) {

            System.out.println(String.format("Oшибка выборки строк: %s", e.getMessage()));
        }
    }

    protected void insertRows(Connection conn) {

        insertRow(conn, "Будьте осмотрительнее в питании, кушайте только исключительно качественные и свежие продукты.");
        insertRow(conn, "Mогут произойти положительные сдвиги в карьере.");
        insertRow(conn, "Рекомендуется посещать увеселительные мероприятия, концертные залы, театры, игровые клубы, рестораны.");
        insertRow(conn, "Вам будет труднее доводить до полного завершения начатые ранее дела.");
        insertRow(conn, "Удастся повысить свой авторитет и вместе с этим поднять самооценку.");
        insertRow(conn, "Будет тянуть к праздному времяпрепровождению.");
        insertRow(conn, "Может усилиться потребность побыть в одиночестве.");
    }

    protected void insertRow(Connection conn, String prediction) {

        try (PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            pstmt.setString(1, prediction);
            pstmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(String.format("Oшибка добавления строки: %s", e.getMessage()));
        }
    }

    protected void createTable(Connection conn) {

        try (Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(CREATE_TABLE_SQL);

        } catch (SQLException e) {

            System.out.println(String.format("Oшибка создания таблицы: %s", e.getMessage()));
        }
    }

    protected Connection connect(String path) {

        String url = "jdbc:sqlite:" + path;
        Connection conn = null;
        try {

            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {

            System.out.println(String.format("Oшибка подключения к базе данных '%s', %s", url, e.getMessage()));
        }

        return conn;
    }
}
