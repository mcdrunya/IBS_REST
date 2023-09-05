package ru.ibs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class APIHelper {
    public static void getFoodList() {
        Response response = given()
                .when()
                .get("/food")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String responseBody = response.getBody().asString();

        assertThat(responseBody, containsString("name"));
        assertThat(responseBody, containsString("type"));
        assertThat(responseBody, containsString("exotic"));
    }

    public static void addNewFood(String foodName, String foodType, Boolean exotic) throws JsonProcessingException {
        Food foodToAdd = new Food();
        foodToAdd.setName(foodName);
        foodToAdd.setType(foodType);
        foodToAdd.setExotic(exotic);

        String requestBody = foodToAdd.toJson();

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/food")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
