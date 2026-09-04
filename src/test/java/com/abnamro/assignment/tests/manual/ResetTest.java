package com.abnamro.assignment.tests.manual;

import com.abnamro.assignment.model.Issue;
import com.abnamro.assignment.tests.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.abnamro.assignment.base.IssuesAPI.deleteIssue;
import static com.abnamro.assignment.base.IssuesAPI.getIssues;

public class ResetTest extends BaseTest {

    // This test is intended to be run manually to reset the project state by deleting all issues.
    // DANGEROUS: Never use in environment containing any useful data.
    @Test
    @Tag("Reset")
    @DisplayName("Deletes all issues in the project")
    public void resetTest() {

        List<Issue> issues = getIssues();
        for (Issue issue : issues) {
            deleteIssue(issue.iid());
        }
    }

}
