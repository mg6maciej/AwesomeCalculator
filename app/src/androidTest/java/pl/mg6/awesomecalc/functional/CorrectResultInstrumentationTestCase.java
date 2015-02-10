package pl.mg6.awesomecalc.functional;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.mg6.awesomecalc.api.BinaryCalculatorService;
import rx.Observable;

import static pl.mg6.awesomecalc.functional.helper.Checker.checkResultTextContains;
import static pl.mg6.awesomecalc.functional.helper.Clicker.clickAdd;
import static pl.mg6.awesomecalc.functional.helper.Clicker.clickOne;
import static pl.mg6.awesomecalc.functional.helper.Clicker.clickResult;
import static pl.mg6.awesomecalc.functional.helper.Clicker.clickZero;

public final class CorrectResultInstrumentationTestCase extends BaseInstrumentationTestCase {

    private static final java.lang.String RESULT = "1000";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setTestModules(new NormalResultModule());
        getActivity();
    }

    public void testShowsCorrectResult() {
        clickOne();
        clickZero();
        clickOne();
        clickAdd();
        clickOne();
        clickOne();
        clickResult();
        checkResultTextContains("Result: " + RESULT);
    }

    @Module(library = true, overrides = true)
    public static final class NormalResultModule {

        @Provides
        @Singleton
        public BinaryCalculatorService provideBinaryCalculatorService() {
            return (leftValue, rightValue) -> Observable.just(RESULT);
        }
    }
}
