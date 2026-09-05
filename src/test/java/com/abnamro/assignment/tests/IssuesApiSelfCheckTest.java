package com.abnamro.assignment.tests;

import com.abnamro.assignment.model.Issue;
import com.abnamro.assignment.model.IssueCreateRequest;
import com.abnamro.assignment.model.IssueUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.abnamro.assignment.base.IssuesAPI.*;
import static org.assertj.core.api.Assertions.assertThat;

// Self-check tests for the newly implemented create/update/delete IssuesAPI methods.
// Not part of the assignment's final test suite - a lightweight sanity check that the
// new API wrappers work end-to-end against the real GitLab instance.
@Tag("UnitTest")
public class IssuesApiSelfCheckTest extends BaseTest {

    @Test
    @DisplayName("Self-check: create, update and delete an issue lifecycle")
    void createUpdateDeleteIssueLifecycle() {
        // Create
        IssueCreateRequest createRequest = new IssueCreateRequest("Self-check issue " + System.currentTimeMillis())
                .setDescription("Created by IssuesApiSelfCheckTest")
                .setLabels("self-check");


        Issue created = createIssue(createRequest);
        assertThat(created).as("Created issue should not be null").isNotNull();
        assertThat(created.title()).isEqualTo(createRequest.getTitle());

        // Update
        IssueUpdateRequest updateRequest = new IssueUpdateRequest()
                .setTitle("Self-check issue updated " + System.currentTimeMillis())
                .setStateEvent("close");

        Issue updated = updateIssue(created.iid(), updateRequest);
        assertThat(updated.title()).isEqualTo(updateRequest.getTitle());
        assertThat(updated.state()).isEqualTo("closed");

        // Delete
        deleteIssue(created.iid());
    }
}
