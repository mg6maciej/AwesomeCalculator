package pl.mg6.awesomecalc.functional;

import pl.mg6.awesomecalc.functional.helper.Checker;
import pl.mg6.awesomecalc.functional.helper.Clicker;

public class CalculatorInstrumentationTestCase extends BaseInstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testShowsNothing() {
        Checker.checkCurrentStateContains("");
    }

    public void testShowsOneAfterClick() {
        Clicker.clickOne();
        Checker.checkCurrentStateContains("1");
    }

    public void testShowsZeroAfterClick() {
        Clicker.clickZero();
        Checker.checkCurrentStateContains("0");
    }

    public void testClearsAfterAddClick() {
        Clicker.clickOne();
        Clicker.clickAdd();
        Checker.checkCurrentStateContains("");
    }
}
