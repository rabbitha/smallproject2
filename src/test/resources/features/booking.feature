Feature: User booking hotel

  Scenario Outline: User booking hotel with login
    Given User access website
    When User log in using the account that has been created
    And User choose hotel
    And User choose hotel room
    And User fill in address
    And User confirm order
    Then User get invoice and user view order history