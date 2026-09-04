package com.abnamro.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitLabUser(
        String state,

        @JsonProperty("web_url")
        String webUrl,

        @JsonProperty("avatar_url")
        String avatarUrl,

        String username,
        long id,
        String name
) {
}