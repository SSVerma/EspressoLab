package com.ssverma.espressolab;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.is;

public class HintMatcher {

    static Matcher<View> withHint(String hint) {
        return withHint(is(hint));
    }

    static Matcher<View> withHint(final Matcher<String> stringMatcher) {
        checkNotNull(stringMatcher);
        return new BoundedMatcher<View, EditText>(EditText.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with Hint: ");
                stringMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(EditText item) {
                String hint = item.getHint().toString();
                return stringMatcher.matches(hint);
            }
        };
    }

}
