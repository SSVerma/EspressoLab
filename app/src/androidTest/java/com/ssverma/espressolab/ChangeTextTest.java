package com.ssverma.espressolab;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ChangeTextTest {

    private static final String TEXT_TO_TYPE = "SS Verma";
    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void changeText_SameActivity() {
        onView(withId(R.id.et_text))/*Find the view*/
                .perform(typeText(TEXT_TO_TYPE), closeSoftKeyboard()); /*Perform action - View actions*/

        onView(withId(R.id.btn_show_text))
                .perform(click());

        onView(withId(R.id.tv_typed_text)) /*Find the View - View matchers*/
                .check(matches(withText(TEXT_TO_TYPE))); /*Validate - View assertions*/

    }

    @Test
    public void changeText_AnotherActivity() {
        onView(withId(R.id.et_text)).perform(typeText(TEXT_TO_TYPE), closeSoftKeyboard());
        onView(withId(R.id.btn_start_activity)).perform(click());
        onView(withId(R.id.tv_text)).check(matches(withText(TEXT_TO_TYPE)));
    }

}
