Feature: Register success
  As a user
  In order to access conversion page
  I need to register
Scenario: valid input
  Given I am on the register page
  When I type the email address "test3@test.com"
  And I provide the birth date "01/01/1980"
  And I provide the street address "Waterloo 1"
  And I provide the zip code "1180"
  And I provide the city name "UCCLE"
  And I provide the country "Belgium"
  And I provide the password  "testtest"
  And I provide the password confirmation "testtest"
  And I click "register"
  Then I should register as a user