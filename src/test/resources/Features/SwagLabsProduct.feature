@SwagLabsCart
Feature: Swag Labs Adding and Removing product from cart Feature

  @AddToCart @SmokeTest @Regression
  Scenario Outline: To Add products to Cart
    Given Navigate to Swag Labs Login page
    When Login by entering "<UserName>" and "<Password>"
    And Click on Add to cart of the products from rows "<rows>"
    Then Validate the product count of cart

    Examples: 
      | UserName      | Password     | rows    |
      | standard_user | secret_sauce | 1,3,4,5 |
      | standard_user | secret_sauce |   2,5,6 |

  @RemoveFromCart @SanityTest @Regression
  Scenario Outline: To Remove products from Cart
    Given Navigate to Swag Labs Login page
    When Login by entering "<UserName>" and "<Password>"
    And Click on Add to cart of the products from rows "<rows>"
    And Click on Remove the products from rows "<rowToBeRemoved>"
    Then Validate the product count of cart

    Examples: 
      | UserName      | Password     | rows    | rowToBeRemoved |
      | standard_user | secret_sauce | 1,3,4,5 |            1,3 |
      | standard_user | secret_sauce |   2,5,6 |              2 |
