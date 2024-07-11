Feature: Manage User Credentials and Interests
  I am a user
  I want to navigate to the home page,
  So that I can insert credentials and select random interests.

  Background:
    Given Navigate to start URL
    Then  Main Page is displayed


  Scenario:Successfully insert credentials and select random interests
    When I click the link in the text 'HERE'
    Then First card is open
    When I input password 'Quietsocks157', email 's.hassan', domain 'a1qa', '.com'
    And  Accept the terms of use, click the 'Next' button
    Then Second card is open
    When I choose 3 random interests, upload an image, and click the 'Next' button
    Then Third card is open
