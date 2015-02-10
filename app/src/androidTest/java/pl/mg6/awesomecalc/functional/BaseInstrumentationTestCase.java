package pl.mg6.awesomecalc.functional;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import pl.mg6.awesomecalc.AwesomeCalcApp;
import pl.mg6.awesomecalc.ui.CalculatorActivity;

public abstract class BaseInstrumentationTestCase extends ActivityInstrumentationTestCase2<CalculatorActivity> {

    public BaseInstrumentationTestCase() {
        super(CalculatorActivity.class);
    }

    protected void setTestModules(Object... testModules) {
        Context context = getInstrumentation().getTargetContext();
        AwesomeCalcApp.setTestModules(context, testModules);
    }
}
