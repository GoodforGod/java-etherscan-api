package io.goodforgod.api.etherscan.model;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Wei {

    private final BigInteger result;

    public Wei(long value) {
        this.result = BigInteger.valueOf(value);
    }

    public Wei(BigInteger value) {
        this.result = value;
    }

    // <editor-fold desc="Getters">
    public BigInteger asWei() {
        return result;
    }

    public BigInteger asKwei() {
        return result.divide(BigInteger.valueOf(1000));
    }

    public BigInteger asMwei() {
        return result.divide(BigInteger.valueOf(1000000));
    }

    public BigInteger asGwei() {
        return result.divide(BigInteger.valueOf(1000000000));
    }

    public BigInteger asEther() {
        return result.divide(BigInteger.valueOf(1000000000000000L));
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
        return "Wei{" +
                "result=" + result +
                '}';
    }
}
