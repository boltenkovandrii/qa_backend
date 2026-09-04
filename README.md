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
