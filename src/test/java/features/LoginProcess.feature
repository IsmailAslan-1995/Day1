@smoke @login
Feature: Product Login

  As a user, we would like to be able to connect successfully with the right credentials.

  RULES :
  - The user must have a valid username
  - The user must have a valid password
  - User will get a generic error message following login attempt with invalid credentials.

  @hamza
  Scenario: The user successfully logs in with valid credentials.
  This scenario tests that a user is able to successfully login when they enter valid credentials.

    Given the user is on the login page
    When the user enters username "Tester"
    And the user enters password as "test"
    And the user clicks on the Login
    Then the user should be logged in
    Then the user arrives at the home page and welcome text containing the username "Tester"
