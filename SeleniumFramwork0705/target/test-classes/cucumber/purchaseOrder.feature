
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background:
	Given I landed on the Ecommerce Page


  @Login
  Scenario Outline: Positive test of submiting the order
    Given Logged in with username <name> and <password>
    When I add product <product1> and <product2> from the cart
    And Checkout and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      |name  					 |password 	 |product1  	 |product2			 |
      |"urfi@gmail.com"|"urfi@1234"|"ZARA COAT 3"|"IPHONE 13 PRO"|

  @NLogin
  Scenario Outline: Positive test of submiting the order
    Given Logged in with username <name> and <password>
    When I add product <product1> and <product2> from the cart
    And Checkout and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      |name  					 |password 	 |product1  	 |product2			 |
      |"urfi@gmail.com"|"urfi@1234"|"ZARA COAT 3"|"IPHONE 13 PRO"|
      
    
  @DeleteProfile
  Scenario Outline: Positive test of submiting the order
    Given Logged in with username <name> and <password>
    When I add product <product1> and <product2> from the cart
    And Checkout and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      |name  					 |password 	 |product1  	 |product2			 |
      |"urfi@gmail.com"|"urfi@1234"|"ZARA COAT 3"|"IPHONE 13 PRO"|