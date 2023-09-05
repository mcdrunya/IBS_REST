package jdbc;

import org.junit.jupiter.api.*;
import javax.sql.DataSource;
import java.io.IOException;

public class JdbcSetup {
    private static DataSource dataSource;
    protected DatabaseHelper foodDb;

    @BeforeAll
    public static void setUp() throws IOException {
        dataSource = DatabaseConfig.createDataSource();
    }

    @BeforeEach
    public void before() {
        foodDb = new DatabaseHelper(dataSource);
    }

    @AfterEach
    public void after() {
        foodDb = null;
    }

    @AfterAll
    public static void afterAll() {
        dataSource = null;
    }
}
