package pl.mg6.awesomecalc.functional;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.mg6.awesomecalc.api.BinaryCalculatorService;
import pl.mg6.awesomecalc.fakeapi.BinaryCalculator;
import pl.mg6.awesomecalc.functional.helper.Checker;
import pl.mg6.awesomecalc.functional.helper.Clicker;
import retrofit.http.Query;
import rx.Observable;

import static pl.mg6.awesomecalc.functional.helper.Clicker.clickAdd;
import static pl.mg6.awesomecalc.functional.helper.Clicker.clickOne;
import static pl.mg6.awesomecalc.functional.helper.Clicker.clickResult;

public final class ResultTests extends BaseInstrumentationTestCase {

    public void testShouldShowCorrectResult() {
        getActivity();
        setTestModules(new TestModule());
        clickOne();
        clickAdd();
        clickOne();
        clickResult();
        Checker.checkResultTextContains("Result: 10");
    }

    @Module(
            library = true,
            overrides = true
    )
    static class TestModule {

        @Provides
        @Singleton
        BinaryCalculatorService provideBinaryCalculatorService() {
            return (leftValue, rightValue) -> {
                String result = new BinaryCalculator().add(leftValue, rightValue);
                return Observable.just(result);
            };
        }
    }
}
