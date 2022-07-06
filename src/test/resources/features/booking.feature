Feature: User booking hotel

  Scenario: User booking hotel with login
    Given User access website
    When User log in using the account that has been created
    And User choose hotel
    And User choose hotel room
    And User fill in address
    And User confirm order
    Then User get invoice and user view order history

  Scenario: User booking hotel without login
    Given User Access website
    When User Choose Hotel
    And User Choose Hotel Room and add more rooms
    And User Confirm order
    Then User get invoice and User view order history

  Scenario: User booking hotel with more than 1 room type
    Given User access website for order
    When User login using the account that has been created
    And User Choose hotel for stay
    And User Choose hotel Room more than one type room
    And User confirm order hotel room
    Then User get invoice order and user view order history

  Scenario: User create account
    Given User Access Website For Create Account
    When User Create Account
    And User input addreess
    Then User have account