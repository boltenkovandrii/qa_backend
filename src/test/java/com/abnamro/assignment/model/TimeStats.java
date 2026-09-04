package com.abnamro.assignment.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public record TimeStats(
        @JsonProperty("time_estimate")
        int timeEstimate,

        @JsonProperty("total_time_spent")
        int totalTimeSpent,

        @JsonProperty("human_time_estimate")
        String humanTimeEstimate,

        @JsonProperty("human_total_time_spent")
        String humanTotalTimeSpent
) {
}