package com.ssverma.espressolab;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ssverma.espressolab.intentstesting.CallActivity;
import com.ssverma.espressolab.intentstesting.ContactsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class IntentsTest {

    private static final String VALID_PHONE = "9461300000";


    private static String PACKAGE_ANDROID_DIALER = "com.android.phone";

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PACKAGE_ANDROID_DIALER = "com.android.server.telecom";
        }
    }

    @Rule
    public IntentsTestRule<CallActivity> intentsTestRule = new IntentsTestRule<>(CallActivity.class);

    @Before
    public void blockExternalIntents() {
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Before
    public void grantPhonePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation().executeShellCommand(
                    "pm grant " + intentsTestRule.getActivity().getPackageName()
                            + " android.permission.CALL_PHONE");
        }
    }

    @Test
    public void pickContact() {
        /*intending -> when of Mockito*/

//        intending(hasComponent(hasShortClassName(".ContactsActivity")))
//                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, ContactsActivity.createPhoneNumberData(VALID_PHONE)));

        intending(toPackage("com.ssverma.espressolab.intentstesting.ContactsActivity"))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, ContactsActivity.createPhoneNumberData(VALID_PHONE)));

        onView(withId(R.id.btn_pick_contact)).perform(click());

        onView(withId(R.id.et_phone)).check(matches(withText(VALID_PHONE)));

    }

    @Test
    public void correctIntentFired() {
        onView(withId(R.id.et_phone)).perform(typeText(VALID_PHONE), closeSoftKeyboard());
        onView(withId(R.id.btn_call)).perform(click());

        /*intended -> verify of Mockito*/
        intended(allOf(toPackage(PACKAGE_ANDROID_DIALER),
                hasAction(Intent.ACTION_CALL),
                hasData(Uri.parse("tel:" + VALID_PHONE))));
    }

}
