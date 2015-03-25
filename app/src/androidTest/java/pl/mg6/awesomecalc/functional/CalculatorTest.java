package pl.mg6.awesomecalc.functional;

import android.os.SystemClock;

import dagger.Module;
import dagger.Provides;
import pl.mg6.awesomecalc.api.BinaryCalculatorService;
import pl.mg6.awesomecalc.functional.helper.Checker;
import pl.mg6.awesomecalc.functional.helper.Clicker;
import retrofit.http.Query;
import rx.Observable;

public class CalculatorTest extends BaseInstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setTestModules(new TestModule());
        getActivity();
    }

    public void testShowsNumberAfterClick() {
        Clicker.clickZero();
        Checker.checkCurrentStateContains("0");
    }

    public void testAddsAndShowsResult() {
        Clicker.clickOne();
        Clicker.clickAdd();
        Clicker.clickOne();
        Clicker.clickResult();
        Checker.checkResultTextContains("Result: 10");
    }

    @Module(overrides = true, library = true)
    public static class TestModule {

        @Provides
        BinaryCalculatorService provideService() {
            return (leftValue, rightValue) -> Observable.just("10");
        }
    }
}
