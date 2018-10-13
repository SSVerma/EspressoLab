package com.ssverma.espressolab;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ssverma.espressolab.custommatcher.CofferPreparationActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class CoffeePreparationTest {

    @Rule
    public ActivityTestRule<CofferPreparationActivity> cofferPreparationRule =
            new ActivityTestRule<>(CofferPreparationActivity.class);

    private String validChoice;
    private String inValidChoice;
    private String validPreparation;

    @Before
    public void init() {
        validChoice = "Anything ending with " + CofferPreparationActivity.VALID_ENDING;
        inValidChoice = "Anything";
        validPreparation = CofferPreparationActivity.COFFEE_PREPARATIONS.get(new Random().nextInt(5));
    }


    @Test
    public void hint_isDisplayed() {
        String hint = cofferPreparationRule.getActivity().getResources().getString(R.string.coffee_hint);
        onView(withId(R.id.et_coffee))
                .check(matches(HintMatcher.withHint(hint)));
    }

    @Test
    public void hint_checkEndsWith() {
        onView(withId(R.id.et_coffee))
                .check(matches(HintMatcher.withHint(anyOf(endsWith("coffee?"), endsWith("tea?")))));
    }

    @Test
    public void validChoiceEntered_WithValidEnding() {
        onView(withId(R.id.et_coffee))
                .perform(typeText(validChoice), closeSoftKeyboard());
        onView(withId(R.id.btn_validate)).perform(click());
        onView(withId(R.id.tv_success)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_fail)).check(matches(not(isDisplayed())));
    }

    @Test
    public void inValidChoiceEntered_WithInValidEnding() {
        onView(withId(R.id.et_coffee))
                .perform(typeText(inValidChoice), closeSoftKeyboard());
        onView(withId(R.id.btn_validate)).perform(click());
        onView(withId(R.id.tv_success)).check(matches(not(isDisplayed())));
        onView(withId(R.id.tv_fail)).check(matches(isDisplayed()));
    }

    @Test
    public void validChoiceEntered_WithValidPreparation() {
        onView(withId(R.id.et_coffee))
                .perform(typeText(validPreparation), closeSoftKeyboard());
        onView(withId(R.id.btn_validate)).perform(click());
        onView(withId(R.id.tv_success)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_fail)).check(matches(not(isDisplayed())));
    }

}
