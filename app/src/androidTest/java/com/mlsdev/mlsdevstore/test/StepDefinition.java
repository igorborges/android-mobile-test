package com.mlsdev.mlsdevstore.test;

import android.content.Intent;
import android.os.SystemClock;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.mlsdev.mlsdevstore.presentaion.AppActivity;
import com.mlsdev.mlsdevstore.test.utils.Ids;
import com.mlsdev.mlsdevstore.test.utils.RecyclerViewMatcher;

import org.junit.Rule;

import java.util.Map;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.mlsdev.mlsdevstore.test.utils.VerticalSwipe.scrollSlowlyDown;
import static com.mlsdev.mlsdevstore.test.utils.VerticalSwipe.scrollSlowlyUp;
import static com.mlsdev.mlsdevstore.test.utils.VerticalSwipe.swipeToBottom;
import static com.mlsdev.mlsdevstore.test.utils.VerticalSwipe.swipeToTop;
import static org.hamcrest.Matchers.not;

public class StepDefinition {
    private Map<String, Integer> ids = Ids.ids();

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Rule
    public static ActivityTestRule<com.mlsdev.mlsdevstore.presentaion.AppActivity> AppActivity = new ActivityTestRule<>(AppActivity.class);

    public void openApp() {
        AppActivity.launchActivity(new Intent());
    }

    public void closeApp() {
        AppActivity.finishActivity();
    }

    public void wait(double timeInSeconds) {
        SystemClock.sleep((long) (timeInSeconds * 1000));
    }

    public void assertPositionInRecyclerView(String recyclerView, int position, String text) {
        onView(withId(ids.get("rv_" + recyclerView))).perform(RecyclerViewActions.actionOnItemAtPosition(position, scrollTo()));
        onView(withRecyclerView(ids.get("rv_" + recyclerView)).atPosition(position)).check(matches(withText(text)));
    }

    public void clickOnText(String text) {
        onView(withText(text)).perform(click());
    }


    public void clickById(String id) {
        onView(withId(ids.get(id))).perform(click());
    }

    public void clickOnRecyclerView(String id, String index) {
        onView(withRecyclerView(ids.get("items_recycler")).atPositionOnView(Integer.parseInt(index), ids.get(id))).perform(click());
    }

    public void assertTextVisible(boolean shouldBeVisible, String textValueOrId) {
        if (shouldBeVisible)
            onView(withText(textValueOrId)).check(matches(isDisplayed()));
        else onView(withText(textValueOrId)).check(matches(not(isDisplayed())));
    }

    public void assertTextById(boolean shouldBeVisible, String id, String text) {
        if (shouldBeVisible)
            onView(withId(ids.get(id))).check(matches(isDisplayed()));
        else
            onView(withId(ids.get(id))).check(matches(withText(text)));
    }

    public void verticalSwipe(boolean shouldSwipeOnePage, boolean shouldScrollDown) {
        if (shouldSwipeOnePage)
            if (shouldScrollDown)
                scrollSlowlyDown();
            else
                scrollSlowlyUp();
        else if (shouldScrollDown)
            swipeToBottom();
        else swipeToTop();
    }

    public void fillInField(String fieldId, String text) {
        onView(withId(ids.get(fieldId))).perform(replaceText(text));
        closeSoftKeyboard();
    }

}
