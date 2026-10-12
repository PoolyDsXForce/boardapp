# CardVault

A JavaFX sports-card collection manager. The MVP provides a collection window and a separate card details window for adding, viewing, editing, and removing cards. Data is held in memory for the current application session only.

## Requirements

- JDK 25
- Maven 3.9+

## Run

```powershell
mvn clean javafx:run
```

## Test

```powershell
mvn clean test
```

See the [approved product backlog](docs/product-backlog.md), the [original Lab 2 product backlog workbook](docs/lab2-product-backlog.xlsx), and the [sprint backlog](docs/sprint-backlog.md) with its [filled spreadsheet template](docs/sprint-backlog.xlsx).

## Contribution workflow

Create feature branches from `dev` and merge them back to `dev` with a pull request. Submit the completed `dev` branch to `release` through a pull request; direct pushes to `release` should be blocked by repository branch protection.

The project's Checkstyle ruleset is maintained at `config/checkstyle/checkstyle.xml`. It is a locally created ruleset because no instructor-provided configuration was available.

## Submission evidence

- [Release branch protection screenshot](docs/submission-screenshots/release-branch-protection.png)
- [Merged dev-to-release pull request screenshot](docs/submission-screenshots/dev-to-release-pull-request.png)
- Feature implementation: [PR #1](https://github.com/PoolyDsXForce/boardapp/pull/1)
- Release: [PR #2](https://github.com/PoolyDsXForce/boardapp/pull/2)
