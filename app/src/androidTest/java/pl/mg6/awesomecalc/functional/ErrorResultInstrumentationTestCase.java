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

public final class ErrorResultInstrumentationTestCase extends BaseInstrumentationTestCase {

    public static final String ERROR_MESSAGE = "Cannot calculate result.";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setTestModules(new ErrorResultModule());
        getActivity();
    }

    public void testShowsCorrectResult() {
        clickOne();
        clickZero();
        clickAdd();
        clickResult();
        checkResultTextContains(ERROR_MESSAGE);
    }

    @Module(library = true, overrides = true)
    public static final class ErrorResultModule {

        @Provides
        @Singleton
        public BinaryCalculatorService provideBinaryCalculatorService() {
            return (leftValue, rightValue) -> Observable.error(new Exception(ERROR_MESSAGE));
        }
    }
}
