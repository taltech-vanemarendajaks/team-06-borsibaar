# Handing over homework 1

## links to all pull requests
https://github.com/taltech-vanemarendajaks/team-06-borsibaar/pulls?q=is%3Apr+is%3Aclosed

## explanation of the conflict you had and how it was resolved 

### BarStationService conflict 
line number 143 containing log error message was changed in two pull requests


### Readme file conflict 
line number 5 was changed in two pull requests

## what each team member worked on
Joonas : Added null param detection before some methods that do not allow null where called in BarStationService. Improved test coverage by adding tests for productmapper (using AAA style). Solved merge conflict with Holger.

Marju: Extracted table section from page.tsx file into an independent component InventoryTable.tsx thus improving readability and maintainability. Reviewed some PR-s, did cleanup.

Holger: Changed logging in BarStationService(to create conflict). Solved Merge conflict with Joonas. Reviewed, commented PR.

Kristina: Created and completed Issue #1 and added the TEAM.md file. Created and completed Issue #2 Add validation for BarStation request, created a feature branch from main via terminal, made changes in multiple commits, pushed the branch, opened a PR, and added reviewers. Performed a rebase via terminal to clean up commit history before merge. Reviewed ProductMapper tests PR #3, ran tests locally via terminal, then squashed and merged the PR. Created conflict-readme branch and intentionally modified the same README line to create a merge conflict. Reviewed and merged Readme PR #9 into main. Resolved the resulting merge conflict locally and pushed the fix. Deleted my merged feature branch using terminal command git branch -d feature/bar-station-validation. Reviewed PR #7.