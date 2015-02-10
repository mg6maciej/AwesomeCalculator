package pl.mg6.awesomecalc.fakeapi;

import java.util.concurrent.TimeUnit;

import pl.mg6.awesomecalc.api.BinaryCalculatorService;
import rx.Observable;

public final class FakeBinaryCalculatorService implements BinaryCalculatorService {

    private final BinaryCalculator calculator = new BinaryCalculator();

    @Override
    public Observable<String> add(String leftValue, String rightValue) {
        try {
            String result = calculator.add(leftValue, rightValue);
            return Observable.just(result).delay(2, TimeUnit.SECONDS);
        } catch (NumberFormatException | NullPointerException ex) {
            return Observable.error(new Exception("Cannot calculate result.", ex));
        }
    }
}
