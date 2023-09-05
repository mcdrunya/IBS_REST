package ru.ibs;


import jdbc.DatabaseConfig;
import jdbc.DatabaseHelper;
import org.junit.jupiter.api.*;
import io.restassured.RestAssured;

import javax.sql.DataSource;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class SetupTests {

    private static DataSource dataSource;
    protected DatabaseHelper foodDb;

    @BeforeAll
    public static void setup() throws IOException {
        RestAssured.baseURI = "http://localhost:8080/api";
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
    public static void afterAll()  {
        dataSource = null;
        given()
                .when()
                .post("/data/reset")
                .then()
                .statusCode(200);
    }

}