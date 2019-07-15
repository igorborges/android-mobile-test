#use the tags to choose which tests should run
@all
@successfullybuy

Feature: Browse and buy an item with success from Health & Beauty and another from Baby categories

#  In order to use the Scenario Outline we need to have the app already opened,
#  and that's why I didn't use the @Before and @After in this demo.
  Scenario: Open app
    Given that the app is opened
    And I wait 5 seconds

  Scenario: Fill in personal information
    When I click on button "account_flow_fragment"
    And I click on button "button_edit_personal_info"
    And I fill in the field "email_edit_text" with the text "davidbrown@gmail.com"
    And I fill in the field "first_name_edit_text" with the text "David"
    And I fill in the field "last_name_edit_text" with the text "Brown"
    And I click on button "submit_button"

    When I click on button "button_edit_shipping_info"
    And I fill in the field "edit_text_phone_number" with the text "5551234"
    And I fill in the field "edit_text_address" with the text "Main Street 111"
    And I fill in the field "edit_text_city" with the text "Brussels"
    And I fill in the field "edit_text_state" with the text "Belgium"
    And I fill in the field "edit_text_postal_code" with the text "12451234"
    And I click on button "submit_button"

    When I click on button "store_flow_fragment"
    When I click on text "browse all categories"

  Scenario Outline: Validate screen element <listText>
    When I assert that the position <listPosition> in the categories list has text "<listText>"
    Examples:
      | listPosition | listText                   |
      | 0            | Collectibles               |
      | 1            | Everything Else            |
      | 2            | Toys & Hobbies             |
      | 3            | Dolls & Bears              |
      | 4            | Stamps                     |
      | 5            | Books                      |
      | 6            | Jewelry & Watches          |
      | 7            | Consumer Electronics       |
      | 8            | Specialty Services         |
      | 9            | Art                        |
      | 10           | Musical Instruments & Gear |
      | 11           | Cameras & Photo            |
      | 12           | Pottery & Glass            |
      | 13           | Sporting Goods             |
      | 14           | Video Games & Consoles     |
      | 15           | Pet Supplies               |
      | 16           | Tickets & Experiences      |
      | 17           | Baby                       |
      | 18           | Travel                     |
      | 19           | eBay Motors                |
    # ... continue with all the items to be validated

  Scenario: Add an item from Health & Beauty category
    Given I assert that the position 30 in the categories list has text "Health & Beauty"
    When I click on text "Health & Beauty"
    And I click on text "100cm Long Straight Cosplay Wig 18 Colors Heat Resistant Women Full Wigs Gift"
    And I click on text "Add to cart"

  Scenario: Add an item from Baby category
    Given I click on button "store_flow_fragment"
    And I click on text "browse all categories"
    And I assert that the position 17 in the categories list has text "Baby"
    When I click on text "Baby"
    And I click on text "EATON CORPORATION 6702ED695 / 6702ED695 (RQANS1)"
    And I click on text "Add to cart"
    And I click on button "cart_flow_fragment"
    And I click on button "button_checkout"

  Scenario: Checkout
    Given I assert that the text "Payment info" is visible
    And I assert that the text "$1969,86" is visible
    When I fill in the field "text_card_number" with the text "4685878128604587"
    And I fill in the field "text_expiration_date" with the text "11/22"
    And I fill in the field "text_cvv" with the text "675"
    And I fill in the field "text_card_holder" with the text "David Brown"
    And I click on button "button_place_order"
#    After this step an issue is popping up with a server error (Something went wrong. Try again later.), so I don't know what i was suppose to validate after the checkout

  Scenario: close app
    Then the app will be closed

