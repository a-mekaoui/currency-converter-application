Feature: Conversion
  As a user
  In order to make conversion with latest rates on the conversion page and have the result
  I need to enter the required base currency, target currency, base amount

Background:
  Given I am logged in with username "test2@test.com" and password "testtest"


Scenario: latest rate conversion
  Given I am on the conversion page
  When I enter an amount of "5"
  And I choose the base currency "AED"
  And I choose the target currency "USD"
  And I enter an historical date of "02/02/2018"
  Then I should have the result of the conversion