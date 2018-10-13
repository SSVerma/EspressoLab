package com.ssverma.espressolab;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ssverma.espressolab.recyclerview.SomeRecyclerActivity;
import com.ssverma.espressolab.recyclerview.SomeRecyclerAdapter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecyclerTest {

    private String itemLabelWithoutIndex;

    @Rule
    public ActivityTestRule<SomeRecyclerActivity> activityTestRule
            = new ActivityTestRule<>(SomeRecyclerActivity.class);

    @Before
    public void init() {
        itemLabelWithoutIndex = activityTestRule.getActivity().getString(R.string.item_label);
    }

    @Test
    public void firstItem_CheckDisplayed() {
        String firstItemText = itemLabelWithoutIndex + String.valueOf(0);
        onView(withText(firstItemText)).check(matches(isDisplayed()));
    }

    @Test
    public void lastItem_CheckDisplayed() {
        String firstItemText = itemLabelWithoutIndex + String.valueOf(SomeRecyclerActivity.TOTAL_ITEMS - 1);
        /*First scroll to particular item to make it visible on screen*/
        onView(withId(R.id.rv_items))
                .perform(RecyclerViewActions.<SomeRecyclerAdapter.ViewHolder>scrollToPosition(SomeRecyclerActivity.TOTAL_ITEMS - 1));
        onView(withText(firstItemText)).check(matches(isDisplayed()));
    }

    @Test
    public void clickMiddleItem_CheckDisplayedInActivity() {
        int middleIndex = SomeRecyclerActivity.TOTAL_ITEMS / 2;

        onView(withId(R.id.rv_items))
                .perform(RecyclerViewActions.<SomeRecyclerAdapter.ViewHolder>actionOnItemAtPosition(middleIndex, click()));

        String itemText = itemLabelWithoutIndex + String.valueOf(middleIndex);

        onView(withId(R.id.tv_item_clicked))
                .check(matches(withText(itemText)));

    }

    @Test
    public void checkItemText_Alternate() {
        int someIndex = 16;
        String itemText = itemLabelWithoutIndex + String.valueOf(someIndex);
        onView(withId(R.id.rv_items)).perform(RecyclerViewActions.scrollToHolder(whereItemTextMatches(itemText)));
        onView(withText(itemText)).check(matches(isDisplayed()));
    }

    private static Matcher<SomeRecyclerAdapter.ViewHolder> whereItemTextMatches(final String text) {
        return new TypeSafeMatcher<SomeRecyclerAdapter.ViewHolder>() {
            @Override
            protected boolean matchesSafely(SomeRecyclerAdapter.ViewHolder item) {
                return text != null && text.equals(item.tvText.getText().toString());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item with text: " + text);
            }
        };
    }

}
