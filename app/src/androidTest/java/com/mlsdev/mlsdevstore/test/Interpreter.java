package com.mlsdev.mlsdevstore.test;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Interpreter {
    private StepDefinition stepDefinition = new StepDefinition();


    @Given("^that the app is opened$")
    public void thatTheAppIsOpened() throws InterruptedException {
        stepDefinition.openApp();
    }

    //    In here I made one step to validate the recycler views and another one to the other views, but it's possible to merge then into one.
    @Given("^I assert that the position ([0-9]*) in the (categories) list has text \"([^\"]*)\"$")
    public void iAssertThatThePositionInTheListHasText(int position, String recyclerView, String text) throws Throwable {
        stepDefinition.assertPositionInRecyclerView(recyclerView, position, text);
    }


    @When("^I click on (text|button|cart list icon) \"([^\"]*)\"( of index )?(\\d*)?$")
    public void iClickOn(String field, String textOrId, String ignore, String index) throws Throwable {
        switch (field) {
            case "text":
                stepDefinition.clickOnText(textOrId);
                break;
            case "button":
                stepDefinition.clickById(textOrId);
                break;
            case "cart list icon":
                stepDefinition.clickOnRecyclerView(textOrId, index);
        }
    }

    @And("^I(?: will|) wait (\\d+) seconds?$")
    public void iWaitSeconds(double timeInSeconds) throws Throwable {
        stepDefinition.wait(timeInSeconds);
    }

    @And("^I (?:will )?assert that the (text|id) \"([^\"]*)\" (?:is|has the text) \"?([^\"]*)\"?$")
    public void iAssertThatTheTextIsVisible(String typeOfField, String textValueOrId, String visibleOrIdValue) throws Throwable {
        boolean shouldBeVisible = visibleOrIdValue.equals("visible");
        switch (typeOfField) {
            case "text":
                stepDefinition.assertTextVisible(shouldBeVisible, textValueOrId);
                break;
            case "id":
                stepDefinition.assertTextById(shouldBeVisible, textValueOrId, visibleOrIdValue);
                break;
        }
    }

    @And("^I (?:will |)swipe (to the|one page) (top|bottom|up|down)(?: of the screen|)$")
    public void iSwipeToTheOfTheScreen(String length, String direction) throws Throwable {
        boolean shouldSwipeOnePage = length.equals("one page");
        boolean shouldScrollDown = (direction.equals("bottom") || direction.equalsIgnoreCase("down"));
        stepDefinition.verticalSwipe(shouldSwipeOnePage, shouldScrollDown);
    }

    @When("^I fill in the field \"([^\"]*)\" with the text \"([^\"]*)\"$")
    public void iFillInTheFieldWithTheText(String field, String text) throws Throwable {
        stepDefinition.fillInField(field, text);
    }

    @Then("^the app will be closed$")
    public void theAppWillBeClosed() {
        stepDefinition.closeApp();
    }
}
