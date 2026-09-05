package com.abnamro.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
public record Issue(
        long id,
        Milestone milestone,
        GitLabUser author,
        String description,
        String state,
        int iid,
        List<GitLabUser> assignees,
        GitLabUser assignee,
        String type,
        List<String> labels,
        int upvotes,
        int downvotes,
        
        @JsonProperty("merge_requests_count")
        int mergeRequestsCount,
        String title,
        
        @JsonProperty("updated_at")
        Instant updatedAt,
        
        @JsonProperty("created_at")
        Instant createdAt,
        
        @JsonProperty("closed_at")
        Instant closedAt,
        
        @JsonProperty("closed_by")
        GitLabUser closedBy,
        boolean subscribed,
        
        @JsonProperty("user_notes_count")
        int userNotesCount,
        
        @JsonProperty("due_date")
        LocalDate dueDate,
        boolean imported,
        
        @JsonProperty("imported_from")
        String importedFrom,
        
        @JsonProperty("web_url")
        String webUrl,
        IssueReferences references,
        
        @JsonProperty("time_stats")
        TimeStats timeStats,
        boolean confidential,
        
        @JsonProperty("discussion_locked")
        Boolean discussionLocked,
        
        @JsonProperty("issue_type")
        String issueType,
        String severity,
        
        @JsonProperty("task_completion_status")
        TaskCompletionStatus taskCompletionStatus,
        Integer weight,
        
        @JsonProperty("has_tasks")
        Boolean hasTasks,
        
        @JsonProperty("_links")
        IssueLinks links,
        
        @JsonProperty("moved_to_id")
        Integer movedToId,
        
        @JsonProperty("service_desk_reply_to")
        String serviceDeskReplyTo
) {}
