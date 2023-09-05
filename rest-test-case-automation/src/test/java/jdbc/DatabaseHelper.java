package jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String INSERT_FOOD_QUERY = "INSERT INTO FOOD (food_name, food_type, food_exotic) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_FOOD_QUERY = "SELECT * FROM FOOD";
    private static final String DELETE_FOOD_QUERY = "DELETE FROM FOOD WHERE food_name = ? AND food_type = ? AND food_exotic = ?";

    private final DataSource dataSource;

    public DatabaseHelper(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addFood(String foodName, String foodType, boolean exotic) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_FOOD_QUERY)) {
            statement.setString(1, foodName);
            statement.setString(2, foodType);
            statement.setBoolean(3, exotic);
            statement.executeUpdate();
        }
    }

    public boolean isFoodExists(String foodName, String foodType, boolean exotic) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM FOOD WHERE food_name = ? AND food_type = ? AND food_exotic = ?")) {
            statement.setString(1, foodName);
            statement.setString(2, foodType);
            statement.setBoolean(3, exotic);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    public boolean isTableExists() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             ResultSet resultSet = connection.getMetaData().getTables(null, null, "FOOD", null)) {
            return resultSet.next();
        }
    }

    public void deleteFood(String foodName, String foodType, boolean exotic) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FOOD_QUERY)) {
            statement.setString(1, foodName);
            statement.setString(2, foodType);
            statement.setBoolean(3, exotic);
            statement.executeUpdate();
        }
    }
}
