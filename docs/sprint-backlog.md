# Sprint Backlog — CardVault MVP

## Sprint goal

Deliver the approved Priority 1 CardVault stories as a tested JavaFX application with a collection window and card details window.

Estimates use person-hours. Week columns are planned remaining effort checkpoints following the supplied sprint backlog template; replace planned values with actual sprint burndown data.

| Related user story | Task description | Initial estimate (hours) | Week 1 remaining | Week 2 remaining | Week 3 remaining | Week 4 remaining |
| --- | --- | ---: | ---: | ---: | ---: | ---: |
| All | Confirm approved MVP scope and record acceptance criteria | 1.0 | 0 | 0 | 0 | 0 |
| CV-1, CV-4, CV-6 | Implement card model validation and unit tests | 3.0 | 0 | 0 | 0 | 0 |
| CV-1, CV-2, CV-6 | Implement collection add/update/remove behavior and tests | 3.0 | 0 | 0 | 0 | 0 |
| CV-2 | Build JavaFX collection window and empty state | 2.0 | 0 | 0 | 0 | 0 |
| CV-1, CV-3, CV-4 | Build card details window with add/edit validation | 3.0 | 0 | 0 | 0 | 0 |
| CV-5 | Add confirmed card deletion flow | 1.0 | 0 | 0 | 0 | 0 |
| CV-6 | Configure JaCoCo coverage gate excluding Main and UI code | 1.0 | 0 | 0 | 0 | 0 |
| CV-6 | Create project Checkstyle rules and enforce them in Maven/CI | 1.5 | 0 | 0 | 0 | 0 |
| All | Configure GitHub Actions for dev pushes and release pull requests | 1.5 | 1.5 | 0.5 | 0 | 0 |
| All | Configure dev/release branch protection, verify full acceptance, and prepare submission ZIP/screenshots | 2.0 | 2.0 | 1.5 | 0 | 0 |
| **Total remaining** |  | **19.0** | **3.5** | **2.0** | **0** | **0** |

The JavaFX UI, model, tests, coverage gate, and project Checkstyle rules have been implemented. The remaining estimates are for repository CI publication, branch controls, final visual acceptance checks, screenshots, and archive preparation.

## Definition of done

- CV-1 through CV-6 acceptance criteria are met.
- `mvn clean verify` succeeds on JDK 25.
- At least 95% line coverage is enforced on non-UI application logic; `Main` and JavaFX UI/codebehind are excluded.
- The repository Checkstyle configuration passes; compare it with the instructor's course rules if they are later provided.
- CI passes on pushes to `dev` and pull requests into `release`.
- `dev` is the default branch, direct pushes to protected `release` are blocked, and the required screenshots and local repository ZIP are prepared.
