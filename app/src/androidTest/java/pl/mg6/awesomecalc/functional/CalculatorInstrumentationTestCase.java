package pl.mg6.awesomecalc.functional;

import pl.mg6.awesomecalc.functional.helper.Checker;
import pl.mg6.awesomecalc.functional.helper.Clicker;

import static pl.mg6.awesomecalc.functional.helper.Checker.checkCurrentStateEquals;
import static pl.mg6.awesomecalc.functional.helper.Clicker.clickAdd;
import static pl.mg6.awesomecalc.functional.helper.Clicker.clickOne;
import static pl.mg6.awesomecalc.functional.helper.Clicker.clickZero;

public final class CalculatorInstrumentationTestCase extends BaseInstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testShouldDeleteCurrentStateAfterAddClick() {
        clickOne();
        clickAdd();
        checkCurrentStateEquals("");
    }

    public void testShouldShowTextAfterClick() {
        clickOne();
        clickZero();
        checkCurrentStateEquals("10");
    }
}
