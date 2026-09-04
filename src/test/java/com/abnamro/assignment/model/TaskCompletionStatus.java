package com.abnamro.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TaskCompletionStatus(
        int count,

        @JsonProperty("completed_count")
        int completedCount
) {
}