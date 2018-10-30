package io.api.model;

import java.math.BigInteger;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Wei {

    private BigInteger result;

    public Wei() {
        this.result = BigInteger.valueOf(0);
    }

    public Wei(long value) {
        this.result = BigInteger.valueOf(value);
    }

    public Wei(BigInteger value) {
        this.result = value;
    }

    //<editor-fold desc="Getters">
    public BigInteger getValue() {
        return result;
    }

    public BigInteger getKwei() {
        return result.divide(BigInteger.valueOf(1000));
    }

    public BigInteger getMwei() {
        return result.divide(BigInteger.valueOf(1000000));
    }

    public BigInteger getGwei() {
        return result.divide(BigInteger.valueOf(1000000000));
    }

    public BigInteger getEther() {
        return result.divide(BigInteger.valueOf(1000000000000000L));
    }
    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wei wei = (Wei) o;

        return result != null ? result.equals(wei.result) : wei.result == null;
    }

    @Override
    public int hashCode() {
        return result != null ? result.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Wei{" +
                "value=" + result +
                '}';
    }
}
