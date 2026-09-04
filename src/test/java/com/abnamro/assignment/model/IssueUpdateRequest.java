package com.abnamro.assignment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record IssueUpdateRequest(
        String title,

        String description,

        List<String> labels,

        @JsonProperty("assignee_ids")
        List<Long> assigneeIds,

        @JsonProperty("milestone_id")
        Long milestoneId,

        Boolean confidential,

        // Kept as String (not LocalDate) so invalid/malformed date values can be sent for edge-case testing.
        @JsonProperty("due_date")
        String dueDate,

        @JsonProperty("issue_type")
        String issueType,

        // "close" or "reopen" - drives issue state transitions.
        @JsonProperty("state_event")
        String stateEvent,

        @JsonProperty("discussion_locked")
        Boolean discussionLocked
) {
}
