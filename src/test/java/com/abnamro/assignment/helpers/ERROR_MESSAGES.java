package com.abnamro.assignment.helpers;

public enum ERROR_MESSAGES {

    NOT_FOUND("404 Not found"),
    UNAUTHORIZED("401 Unauthorized"),
    FORBIDDEN("403 Forbidden"),
    BAD_REQUEST("400 Bad Request"),
    INTERNAL_SERVER_ERROR("500 Internal Server Error");

    private final String message;

    ERROR_MESSAGES(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
