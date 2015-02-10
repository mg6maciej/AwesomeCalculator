package pl.mg6.awesomecalc;

import org.junit.Test;

import pl.mg6.awesomecalc.fakeapi.BinaryCalculator;

import static org.assertj.core.api.Assertions.assertThat;

public final class NewBinaryCalculatorTest {

    @Test
    public void shouldAddNumbersCorrectly() {
        BinaryCalculator calculator = new BinaryCalculator();

        String result = calculator.add("11", "1010");

        assertThat(result).isEqualTo("1101");
    }
}
