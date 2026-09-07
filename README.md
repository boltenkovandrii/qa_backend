# Candidate README Template

> Please refer to `#ASSIGNMENT.md` for detailed instructions before starting this assignment.

Include a `README.md` in your submission. Do not include a pre-filled template; provide your own README that documents your assignment.

Please include:
- A brief description of your solution and any assumptions.
- Build and run instructions (Java version and Maven commands).
- How to run tests.
- Any special notes, dependencies, or trade-offs.re starting this assignment.

Keep this file updated with any special instructions or dependencies so reviewers can run your solution easily.



-------------------------------------------------------------------------------------------------------------
## Project setup

### Prerequisites
- Java 17 (JDK)
- Maven 3.6+
- GitLab account with a application configured and personal access token (PAT) with `api` scope. It is supposed that use has admin rights in the test project.

### Configuration
- Set GITLAB_PROJECT_ID and GITLAB_USER_ID `src/test/resources/config.properties`
- Create file `src/test/resources/gitlab.properties` based on provided sample and store your GitLab personal access token (PAT) in it. 
<span style="color:#AA2222">**The file is ignored by git and should never be committed to the repository.**</span>
- For CI runs, create environment variable `GITLAB_ACCESS_TOKEN` with personal access token (Settings-> Secrets and variables-> Actions-> New repository secret).

### Test run   
The simplest command to run the tests with Maven wrapper (all commands provided for Windows environment):
```powershell
./mvnw clean test; ./mvnw allure:report
```
This should run the tests and generate an Allure report. See more information about test scopes and runs below.



## Limitations and scope:

1. Following API's are covered:
   * https://docs.gitlab.com/api/issues/#list-all-project-issues
   * https://docs.gitlab.com/api/issues/#retrieve-a-project-issue
   * https://docs.gitlab.com/api/issues/#create-an-issue
   * https://docs.gitlab.com/api/issues/#update-an-issue
   * https://docs.gitlab.com/api/issues/#delete-an-issue
2. Only Free plan is covered — no Premium or Ultimate logic or related fields in DTO's are covered.
3. A single user with admin rights is used for testing. The user's ID is provided through configuration and is used where a valid user ID is required, for example when assigning an issue. 
Restrictions and differences related to non-admin user permissions are not covered in this assignment.
4. Fields that require additional project data are not covered in the create/update tests. This includes `milestoneId`, `milestone`, `merge_request_to_resolve_discussions_of`, and `discussion_to_resolve`, 
as testing these fields properly would require creating and maintaining additional milestones or merge requests in the test project. 



## Test Execution Tags

Tests are grouped by their expected environment state and execution mode.

| Tag | Intended usage | Environment |
|---|---|---|
| `UnitTest` | Normal API tests. Tests should be independent where possible and may run in parallel. | May contain test data |
| `Reset` | Manually resets the test project by deleting **all issues**. Use when a completely clean environment is required. | Must contain data; all data is removed |
| `Cleanup` | Manually removes issues created by the test suite using the `[API-TEST]` test-data prefix. Useful for cleaning up after interrupted or failed test runs. | May contain test data |
| `EmptyEnvironmentTests` | Tests API behaviour when the project contains no issues. Run manually after `Reset`. | Must be empty |

### Recommended usage

#### For normal test execution:

```powershell
./mvnw clean test; ./mvnw allure:report
```

The environment may contain existing data, but tests should avoid modifying it or data belonging to other tests.

#### For running the tests that require no existing data, you need to reset the project to an empty state. This is manual step:

Clean the environment <span style="color:#AA2222">**WARNING: This will delete all issues in the test project**</span>
```powershell
./mvnw clean test -Dscope=Reset; ./mvnw allure:report
```
then run the tests

```powershell
./mvnw clean test -Dscope=EmptyEnvironmentTests -Dthreads=1; ./mvnw allure:report
```

`Reset` is destructive and should only be used against the dedicated test project.

`Cleanup` is intended as a recovery/maintenance operation after test development or failed runs. It removes only issues identified as test data by the `[API-TEST]` prefix.

`EmptyEnvironmentTests` should be executed separately from tests that create, update, or delete issues. When isolation from other test classes is required, run them with a single thread:
