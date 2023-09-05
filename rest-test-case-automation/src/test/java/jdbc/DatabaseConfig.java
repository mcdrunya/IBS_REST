package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;

public class DatabaseConfig {
    private static final String CONFIG_FILE = "db.properties";

    public static DataSource createDataSource() throws IOException {
        Properties properties = loadProperties(CONFIG_FILE);

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(properties.getProperty("db.url"));
        dataSource.setUser(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));

        return dataSource;
    }

    private static Properties loadProperties(String configFile) throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream(configFile)) {
            properties.load(inputStream);
        }
        return properties;
    }
}

