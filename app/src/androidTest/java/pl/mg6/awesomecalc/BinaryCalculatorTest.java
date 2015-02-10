package pl.mg6.awesomecalc;

import junit.framework.TestCase;

import pl.mg6.awesomecalc.fakeapi.BinaryCalculator;

public final class BinaryCalculatorTest extends TestCase {

    public void testAddsNumbersCorrectly() {
        BinaryCalculator calculator = new BinaryCalculator();

        String result = calculator.add("11", "1010");

        assertEquals("1101", result);
    }
}
