package com.abnamro.assignment.helpers;

import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

//TODO: check if needed
public class AssertionHelpers {

    public static void assertStatus(Response response, int expectedStatusCode) {
        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);
    }

    public static void assertError(Response response, int expectedStatusCode, String expectedMessage) {
        assertStatus(response, expectedStatusCode);
        assertThat(response.jsonPath().getString("message")).contains(expectedMessage);
    }

    public static void assertError(Response response, int expectedStatusCode, ERROR_MESSAGES expectedMessage) {
        assertStatus(response, expectedStatusCode);
        assertThat(response.jsonPath().getString("message")).contains(expectedMessage.getMessage());
    }



}
