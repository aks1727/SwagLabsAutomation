@SwagLabsLogin 
Feature: Swag Labs Login Feature

  @Login @SmokeTest @Regression
  Scenario Outline: Validate login data in Swag Labs Website
    Given Navigate to Swag Labs Login page
    When Enter username and password from row "<rowNum>"
    Then Validate if redirected to product page

    Examples: 
      | rowNum |
      | 1      |
      | 2      |
      | 3      |
      | 4      |
      | 5      |
      | 6      |
