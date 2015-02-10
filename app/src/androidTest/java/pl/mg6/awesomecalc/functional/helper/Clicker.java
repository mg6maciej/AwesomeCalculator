package pl.mg6.awesomecalc.functional.helper;

import pl.mg6.awesomecalc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public final class Clicker {

    public static void clickZero() {
        clickViewWithId(R.id.calculator_zero);
    }

    public static void clickOne() {
        clickViewWithId(R.id.calculator_one);
    }

    public static void clickAdd() {
        clickViewWithId(R.id.calculator_add);
    }

    public static void clickResult() {
        clickViewWithId(R.id.calculator_result);
    }

    private static void clickViewWithId(int viewId) {
        onView(withId(viewId)).perform(click());
    }
}
