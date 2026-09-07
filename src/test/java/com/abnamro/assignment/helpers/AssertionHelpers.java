package com.abnamro.assignment.helpers;

import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

//TODO: check if should be moved to BaseTest
public class AssertionHelpers {

    public static void assertStatus(Response response, int expectedStatusCode) {
        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);
    }

    public static void assertMessage(Response response, int expectedStatusCode, String expectedMessage) {
        assertStatus(response, expectedStatusCode);
        assertThat(response.jsonPath().getString("message")).contains(expectedMessage);
    }

    public static void assertError(Response response, int expectedStatusCode, String expectedMessage) {
        assertStatus(response, expectedStatusCode);
        assertThat(response.jsonPath().getString("error")).contains(expectedMessage);
    }

}
