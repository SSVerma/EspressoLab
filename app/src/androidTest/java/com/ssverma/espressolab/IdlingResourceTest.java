package com.ssverma.espressolab;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ssverma.espressolab.idlingresources.IdlingResSampleActivity;

import org.junit.After;
import org.junit.Before;
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
public class IdlingResourceTest {

    private CountingIdlingResource idlingResource;

    @Rule
    public ActivityTestRule<IdlingResSampleActivity> sampleActivityRule
            = new ActivityTestRule<>(IdlingResSampleActivity.class);

    @Before
    public void registerIdlingResource() {
        idlingResource = sampleActivityRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void changeText_Displayed() {
        onView(withId(R.id.et_text))
                .perform(typeText("Mr Stark"), closeSoftKeyboard());

        onView(withId(R.id.btn_process_message)).perform(click());

        onView(withId(R.id.tv_text)).check(matches(withText("Mr Stark")));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource == null) {
            return;
        }
        IdlingRegistry.getInstance().unregister(idlingResource);
    }


}
