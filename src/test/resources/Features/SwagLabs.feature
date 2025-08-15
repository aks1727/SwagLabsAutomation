@SwagLabCompleteAutomation @Regression @Smoke
Feature: Swag Labs Adding and Removing product from cart Feature

  @AddToCart @Positive
  Scenario Outline: To Add products to Cart
    Given Navigate to Swag Labs Login page
    When Login by entering "<UserName>" and "<Password>"
    And Click on Add to cart of the products from rows "<rows>"
    And Enter "<FirstName>", "<LastName>" and "<PostalCode>" in YourInformation Page and click on Continue
    Then Verify Details on Overview page and click on Finish
    And Verify the confimation message

    Examples: 
      | UserName      | Password     | rows    | FirstName | LastName | PostalCode |
      | standard_user | secret_sauce | 1,3,4,5 | Sam       | Altman   | 12345      |
      | standard_user | secret_sauce | 2,5,6   | Prakash   | Pandey   | 54321      |
