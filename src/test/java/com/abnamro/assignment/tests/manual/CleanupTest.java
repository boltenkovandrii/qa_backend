package com.abnamro.assignment.tests.manual;

import com.abnamro.assignment.model.Issue;
import com.abnamro.assignment.tests.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.abnamro.assignment.base.IssuesAPI.*;

public class CleanupTest extends BaseTest {


    // This test is intended to be run manually to reset the project state by deleting all issues created (and not cleaned) by tests.
    // NOTE: This test will only delete issues that start with the prefix defined in the TEST_ISSUE_PREFIX constant. Most issues created by tests should have this prefix, it is not 100% guaranteed.
    // DANGEROUS: For environments with useful data, make sure that no other issues start with the same prefix, otherwise they will be deleted as well.

    @Test
    @Tag("Cleanup")
    @DisplayName("Cleaning up the test issues")
    public void cleanupTest() {

        List<Issue> issues = getIssues();
        for (Issue issue : issues) {
            if (issue.title().startsWith(TEST_ISSUE_PREFIX)) {
                deleteIssue(issue.iid());
            }
        }
    }


}
