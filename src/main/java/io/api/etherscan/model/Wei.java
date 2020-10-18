package io.api.etherscan.model;

import java.math.BigInteger;
import java.util.Objects;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Wei {

    private BigInteger result;

    public Wei(BigInteger value) {
        this.result = value;
    }

    // <editor-fold desc="Getters">
    public BigInteger getValue() {
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
        if (o == null || getClass() != o.getClass())
            return false;

        Wei wei = (Wei) o;
        return Objects.equals(result, wei.result);
    }

    @Override
    public int hashCode() {
        return result != null ? result.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Wei{" +
                "result=" + result +
                '}';
    }
}
