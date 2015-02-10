package pl.mg6.awesomecalc.functional;

import static pl.mg6.awesomecalc.functional.helper.Checker.checkCurrentStateContains;
import static pl.mg6.awesomecalc.functional.helper.Clicker.clickAdd;
import static pl.mg6.awesomecalc.functional.helper.Clicker.clickOne;

public final class CalculatorInstrumentationTestCase extends BaseInstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testCanEnterNumber() {
        clickOne();
        clickOne();
        checkCurrentStateContains("11");
    }

    public void testClearsAfterPressingAdd() {
        clickOne();
        clickAdd();
        checkCurrentStateContains("");
    }
}
