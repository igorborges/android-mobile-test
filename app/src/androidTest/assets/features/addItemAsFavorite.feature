#use the tags to choose which tests should run
@all
@additemasfavorite

Feature: Add an item as favorite

#  In order to use the Scenario Outline we need to have the app already opened,
#  and that's why I didn't use the @Before and @After in this demo.
  Scenario: Open app
    Given that the app is opened
    And I wait 5 seconds
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

  Scenario: Select an item and verify the data
    When I click on text "Travel"
    And I click on text "3PCS Luggage Sets Hard Shell Suitcase Travel Bag Trolley Spinner ABS Business  "
    Then I will assert that the text "3PCS Luggage Sets Hard Shell Suitcase Travel Bag Trolley Spinner ABS Business  " is visible
    And I assert that the id "item_details_price" is "89.99"
    And I assert that the id "item_details_currency" is "USD"
    And I assert that the text "description" is visible
    And I swipe to the bottom of the screen
    And I assert that the id "tv_condition_value" is "New without tags"
    And I assert that the id "tv_brand_value" is "Freetrade"
    And I assert that the id "tv_gender_value" is "Unisex Adult"
    And I assert that the id "tv_color_value" is "Pink"
    And I assert that the id "tv_material_value" is "ABS Plastic"
    And I swipe to the top of the screen
    And I assert that the id "store_flow_fragment" is visible
    And I assert that the id "cart_flow_fragment" is visible
    And I assert that the id "favorites_flow_fragment" is visible
    And I assert that the id "account_flow_fragment" is visible

  Scenario: 1 - Add an item as favorite
    When I click on button "add_to_favorites"
    And I click on button "favorites_flow_fragment"
    Then I will assert that the text "3PCS Luggage Sets Hard Shell Suitcase Travel Bag Trolley Spinner ABS Business  " is visible

  Scenario: Remove the item from the favorites (go back to the initial state)
    When I click on text "3PCS Luggage Sets Hard Shell Suitcase Travel Bag Trolley Spinner ABS Business  "
    And I click on button "add_to_favorites"
    And I click on button "favorites_flow_fragment"
    Then I will assert that the text "Save something :)" is visible

  Scenario: close app
    Then the app will be closed