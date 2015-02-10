package pl.mg6.awesomecalc.functional.helper;

import pl.mg6.awesomecalc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public final class Checker {

    public static void checkCurrentStateContains(String text) {
        checkViewWithIdContains(R.id.calculator_current_state, text);
    }

    public static void checkResultTextContains(String text) {
        checkViewWithIdContains(R.id.result_text, text);
    }

    private static void checkViewWithIdContains(int viewId, String text) {
        onView(withId(viewId)).check(matches(withText(text)));
    }
}
