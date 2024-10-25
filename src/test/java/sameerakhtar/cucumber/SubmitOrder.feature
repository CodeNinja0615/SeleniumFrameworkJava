@tag
Feature: Purchase an item from an Ecommerece website
  I want to use this template for my feature file

Background: 
Given I landed on Ecommerce page
  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> in cart
    And Checkout <productName> and submit the order
    Then I verify the "THANKYOU FOR THE ORDER." message on comfirmation page
		And I quit the application
    Examples: 
      | name  										 | password 		 | productName |
   	  | akhtarsameer743@gmail.com  | Sameerking01! | ZARA COAT 3 |
      | sameerakhtar1513@gmail.com | Sameerking01! | ZARA COAT 3 |
