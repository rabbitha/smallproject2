Feature: Login
  Scenario: User Login with valid data
    Given user open url saucedemo.com
    When user input username field with "standard_user"
    And user input password field with "secret_sauce"
    And user click Login
    Then user see result that direct to homepage

#  Scenario: User Login with invalid data
#    Given user open url saucedemo.com
#    When user input username field with "invalidtest"
#    And user input password field with "secret_sauce"
#    And user click Login
#    Then user see result that consist validation message "Username and password do not match any user in this service"
#    And redirect to homepage should not occured