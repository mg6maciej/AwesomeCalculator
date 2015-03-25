package pl.mg6.awesomecalc.functional;

import dagger.Module;
import dagger.Provides;
import pl.mg6.awesomecalc.api.BinaryCalculatorService;
import pl.mg6.awesomecalc.functional.helper.Checker;
import pl.mg6.awesomecalc.functional.helper.Clicker;
import rx.Observable;

public class ResultInstrumentationTestCase extends BaseInstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setTestModules(new TestModule());
        getActivity();
    }

    public void testShowsCorrectResult() {
        Clicker.clickOne();
        Clicker.clickOne();
        Clicker.clickAdd();
        Clicker.clickOne();
        Clicker.clickOne();
        Clicker.clickZero();
        Clicker.clickResult();
        Checker.checkResultTextContains("Result: 1001");
    }

    @Module(overrides = true, library = true)
    public static class TestModule {

        @Provides
        public BinaryCalculatorService provideService() {
            return (leftValue, rightValue) -> Observable.just("1001");
        }
    }
}
