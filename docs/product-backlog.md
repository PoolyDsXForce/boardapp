# CardVault MVP Product Backlog

## Approved MVP scope

The approved MVP is a JavaFX desktop application with two windows: a collection window and a card details window. A user can add a sports card, browse the collection, inspect a card's details, edit it, and remove it. Card data is kept in memory for the current run and is not saved when the application closes.

The scope is based on Priority 1 stories in the supplied Lab 2 CardVault product backlog. The sports-card fields are player, sport, year, set/brand, card number, condition, notes, and estimated value.

## Product backlog

| ID | Priority | User story | Acceptance criteria |
| --- | --- | --- | --- |
| CV-1 | 1 | As a collector, I want to add a card to my collection so I can keep an organized record of cards I own. | The card details window accepts required card details and a non-negative estimated value; invalid or missing values are rejected with visible feedback; a valid card appears in the collection. |
| CV-2 | 1 | As a collector, I want to view my collection so I can browse cards I have added. | The collection window lists cards with player, year, set, card number, and estimated value; an empty-state message is shown when the collection is empty. |
| CV-3 | 1 | As a collector, I want to view card details so I can review its condition, notes, and value. | Selecting a card opens a separate details window populated with all card fields. |
| CV-4 | 1 | As a collector, I want to edit card details so I can update changing information. | Saving edits updates the selected card without changing its identity or position in the collection; invalid edits are rejected and the existing card remains unchanged. |
| CV-5 | 1 | As a collector, I want to delete a card so I can remove cards I no longer own. | Deletion requires confirmation and removes only the selected card; cancelling leaves the collection unchanged. |
| CV-6 | Must | As a maintainer, I want core collection logic tested so regressions are caught. | Tests cover required-field and value validation, add/update/delete behavior, duplicate IDs, ordering, and immutable collection snapshots; CI enforces the repository Checkstyle rules and a 95% line-coverage gate outside the UI. |

## Out of scope

- Grading submissions and results, market-value history, and sales status or sale notes (Priority 2 and 3 stories).
- Accounts, cross-device access, export, filtering, sorting, and grading statistics (Priority 4 and 5 stories).
- Database/file persistence and network access.

## Approval

The project owner approved this scope before implementation.
