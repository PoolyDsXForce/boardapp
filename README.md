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

See [the product backlog](docs/product-backlog.md) and [the sprint backlog](docs/sprint-backlog.md) for scope and acceptance criteria.

## Contribution workflow

Create feature branches from `dev` and merge them back to `dev` with a pull request. Submit the completed `dev` branch to `release` through a pull request; direct pushes to `release` should be blocked by repository branch protection.
