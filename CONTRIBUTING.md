# Contributing Code or Documentation Guide

## Running Tests

The new code should contain tests that check new behavior.

Run tests `./gradlew test` to check that code works as behavior.

## Code Style

The code base should remain clean, following industry best practices for organization, javadoc and style, as much as possible.

To run the Code Style check use `./gradlew spotlessCheck`.

If check found any errors, you can apply Code Style by running `./gradlew spotlessApply`

## Creating a pull request

Once you are satisfied with your changes:

- Commit changes to the local branch you created.
- Push that branch with changes to the corresponding remote branch on GitHub
- Submit a [pull request](https://help.github.com/articles/creating-a-pull-request) to `dev` branch.