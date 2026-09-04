package com.abnamro.assignment.tests.manual;

import com.abnamro.assignment.model.Issue;
import com.abnamro.assignment.model.IssueCreateRequest;
import com.abnamro.assignment.model.IssueUpdateRequest;
import com.abnamro.assignment.tests.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.abnamro.assignment.base.IssuesAPI.*;
import static com.abnamro.assignment.helpers.AssertionHelpers.assertError;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("EmptyEnvironmentTests")
public class EmptyEnvironmentTest extends BaseTest {

    /*
     * These tests require the project to contain no issues.
     * Run manually after ResetTest.
     *
     * Test methods in this class must execute sequentially because
     * createIssueEmptyEnvTest temporarily changes the environment.
     */


    @Test
    @DisplayName("Retrieve issue list for an empty environment")
    public void getIssuesEmptyEnvTest() {
        List<Issue> issues = getIssues();
        assertThat(issues).as("Check that issues  response is not null").isNotNull();
        assertThat(issues).as("Check the issue id").hasSize(0);
    }

    @Test
    @DisplayName("Attempt to retrieve issue for an empty environment")
    public void getIssueEmptyEnvTest() {
        Response response = getIssueRaw(PROJECT_ID, 1);
        assertError(response, 404, NOT_FOUND_MESSAGE);
    }

    @Test
    @DisplayName("Attempt to delete issue for an empty environment")
    public void deleteIssueEmptyEnvTest() {
        Response response = deleteIssueRaw(PROJECT_ID, 1);
        assertError(response, 404, ISSUE_NOT_FOUND_MESSAGE);
    }

    @Test
    @DisplayName("Attempt to update issue for an empty environment")
    public void updateIssueEmptyEnvTest() {
        IssueUpdateRequest updateRequest = new IssueUpdateRequest(generateUniqueIssueTitle("Update for empty env test"),
                null, null, null, null, null, null, null, "close",null);

        Response response = updateIssueRaw(PROJECT_ID, 1, updateRequest);
        assertError(response, 404, NOT_FOUND_MESSAGE);
    }


    @Test
    @DisplayName("Create issue for an empty environment")
    public void createIssueEmptyEnvTest() {
        IssueCreateRequest createRequest = new IssueCreateRequest(
                generateUniqueIssueTitle("Create for empty env test"),
                "Create for empty env test",
                List.of("self-check"),
                null,
                null,
                null,
                null,
                null
        );
        Issue created = createIssue(createRequest);

        try {
            assertThat(created).as("Created issue should not be null").isNotNull();
            assertThat(created.title()).isEqualTo(createRequest.title());
        } finally {
            // Clean up
            if(created!=null){
                deleteIssue(created.iid());
            }
        }
    }

}

