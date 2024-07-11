Feature: Navigate to Home Page and Hide Help Form
  As a user,
  I want to navigate to the home page and hide the help form,
  So that I can see the welcome page and hide the help form content.

  Background:
    Given Navigate to start URL
    Then  Main Page is displayed

  Scenario: Hide help form
    When I click the link in the text 'HERE'
    Then First card is open
    When I hide the help form
    Then Form content is hidden