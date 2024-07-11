Feature: Navigate and Validate Timer
  As a user,
  I want to navigate to the home page,
  So that I can validate that the timer starts from "00:00".

  Background:
    Given Navigate to start URL
    Then  Main Page is displayed

  Scenario: Validate timer start
    When I click the link in the text 'HERE'
    Then First card is open
    And The timer starts from '00:00:00'