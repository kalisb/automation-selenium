@login
Feature: User can log in their account
 Scenario: Login Success and Failure
    Given I open puffin main page
    When I enter my valid user credentials
    Then verify that main menu is shown
    When I click logout
    Then login page is shown
    When I enter invalid credentials
    Then error message is shown