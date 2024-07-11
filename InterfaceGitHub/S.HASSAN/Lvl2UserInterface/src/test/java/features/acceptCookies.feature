Feature: Navigate and Accept Cookies
  As a user,
  I want to navigate to the home page and accept cookies,
  So that I can close the cookie consent form.

  Background:
    Given Navigate to start URL
    Then  Main Page is displayed

  Scenario: Accept cookies
    When I click the link in the text 'HERE'
    Then First card is open
    When I accept the cookies
    Then Cookies form is closed