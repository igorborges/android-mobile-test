#use the tags to choose which tests should run
@all
@cartcrud

Feature: Create, Read, Update and Delete from the cart

#  In order to use the Scenario Outline we need to have the app already opened,
#  and that's why I didn't use the @Before and @After in this demo.
  Scenario: Open app
    Given that the app is opened

  Scenario: Add to the cart
    When I click on text "100cm Long Straight Cosplay Wig 18 Colors Heat Resistant Women Full Wigs Gift"
    And I click on text "Add to cart"

  Scenario: Read from the cart
    When I click on button "cart_flow_fragment"
    Then I will assert that the text "100cm Long Straight Cosplay Wig 18 Colors Heat Resistant Women Full Wigs Gift" is visible
    And I will assert that the text "17" is visible
    And I will assert that the text "49" is visible
    And I will assert that the text "USD" is visible
    And I will assert that the text "$17,49" is visible
    And I will assert that the id "button_checkout" has the text "Checkout"

  Scenario: Delete from the cart
    When I click on cart list icon "button_remove_from_cart" of index 0
    Then I will assert that the text "No items - no fun :(" is visible

  Scenario: close app
    Then the app will be closed
