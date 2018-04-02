Feature: Login
  As a user
  In order to access conversion page
  I need to login
Scenario: login
  Given I am on the login page
  When I enter the email address "test2@test.com"
  And I provide the password "testtest"
  And I click at "login"
  Then I should be logged in and the current page is conversion