package io.goodforgod.api.etherscan.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Wei {

    private final BigInteger result;

    private Wei(BigInteger value) {
        this.result = value;
    }

    public static Wei ofWei(int value) {
        return new Wei(BigInteger.valueOf(value));
    }

    public static Wei ofWei(long value) {
        return new Wei(BigInteger.valueOf(value));
    }

    public static Wei ofWei(BigInteger value) {
        return new Wei(value);
    }

    public static Wei ofEther(int value) {
        return new Wei(BigInteger.valueOf(value).multiply(BigInteger.valueOf(1_000_000_000_000_000L)));
    }

    public static Wei ofEther(long value) {
        return new Wei(BigInteger.valueOf(value).multiply(BigInteger.valueOf(1_000_000_000_000_000L)));
    }

    public static Wei ofEther(BigInteger value) {
        return new Wei(value.multiply(BigInteger.valueOf(1_000_000_000_000_000L)));
    }

    public static Wei ofEther(BigDecimal value) {
        return new Wei(value.multiply(BigDecimal.valueOf(1_000_000_000_000_000L)).toBigInteger());
    }

    // <editor-fold desc="Getters">
    public BigInteger asWei() {
        return result;
    }

    public BigDecimal asKwei() {
        return new BigDecimal(result).divide(BigDecimal.valueOf(1_000), RoundingMode.HALF_UP);
    }

    public BigDecimal asMwei() {
        return new BigDecimal(result).divide(BigDecimal.valueOf(1_000_000), RoundingMode.HALF_UP);
    }

    public BigDecimal asGwei() {
        return new BigDecimal(result).divide(BigDecimal.valueOf(1_000_000_000), RoundingMode.HALF_UP);
    }

    public BigDecimal asEther() {
        return new BigDecimal(result).divide(BigDecimal.valueOf(1_000_000_000_000_000L), RoundingMode.HALF_UP);
    }
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Wei))
            return false;
        Wei wei = (Wei) o;
        return Objects.equals(result, wei.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
