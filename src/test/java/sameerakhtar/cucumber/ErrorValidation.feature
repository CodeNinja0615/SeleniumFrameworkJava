
@tag
Feature: Error Validation
  I want to use this template for my feature file
  
  @ErrorValidation
  Scenario Outline: Positive Test of submitting the order
  	Given I landed on Ecommerce page
    When Logged in with username <name> and password <password>
    Then I verify the "Incorrect email or password." message
		And I quit the application
    Examples: 
      | name  										 | password 	  | 
   	  | akhtarsameer743@gmail.com  | Sameerking01 | 
      | sameerakhtar1513@gmail.com | Sameerking01 | 
