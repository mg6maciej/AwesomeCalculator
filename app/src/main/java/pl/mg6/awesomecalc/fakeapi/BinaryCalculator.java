package pl.mg6.awesomecalc.fakeapi;

import java.math.BigInteger;

public final class BinaryCalculator {

    private static final int RADIX = 2;

    public String add(String leftValue, String rightValue) {
        BigInteger left = parse(leftValue);
        BigInteger right = parse(rightValue);
        BigInteger result = left.add(right);
        return result.toString(RADIX);
    }

    private BigInteger parse(String value) {
        return new BigInteger(value, RADIX);
    }
}
