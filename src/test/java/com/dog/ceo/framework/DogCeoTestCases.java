package com.dog.ceo.framework;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class DogCeoTestCases {

    private static final String BASE_URL = "https://dog.ceo/api";

    @Test
    public void returnsSuccessfulMessageWhenUserSearchesForRandomBreeds() {

        RestAssured.get(BASE_URL + "/breeds/image/random")
                .then()
                .assertThat()
                .body("message", notNullValue())
                .body("status", notNullValue())
                .body("message", containsString("https://images.dog.ceo/breeds/"))
                .body("status", equalTo("success"))
                .statusCode(200);
    }

    @Test
    public void isBulldogOnListOfAllBreeds() {

        RestAssured.get(BASE_URL + "/breeds/list/all")
                .then()
                .assertThat()
                .body("message", notNullValue())
                .body("status", notNullValue())
                .body("message", hasKey("bulldog"))
                .body("message.bulldog", notNullValue())
                .body("status", equalTo("success"))
                .statusCode(200);
    }

    @Test
    public void retrieveAllSubBreedsForBulldogsAndTheirRespectiveImages() {

        RestAssured.get(BASE_URL + "/breed/bulldog/images")
                .then()
                .assertThat()
                .body("message", notNullValue())
                .body("status", notNullValue())
                .body("message", hasItem(containsString("https://images.dog.ceo/breeds/bulldog-")))
                .body("message.size()", greaterThan(300))
                .body("status", equalTo("success"))
                .statusCode(200);
    }

}
