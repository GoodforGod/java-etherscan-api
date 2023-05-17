package io.goodforgod.api.etherscan.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Wei implements Comparable<Wei> {

    private static final BigDecimal KWEI_POW = BigDecimal.ONE.pow(3);
    private static final BigDecimal MWEI_POW = BigDecimal.ONE.pow(6);
    private static final BigDecimal GWEI_POW = BigDecimal.ONE.pow(9);
    private static final BigDecimal WEI_POW = BigDecimal.ONE.pow(18);

    private final BigInteger result;

    private Wei(BigInteger value) {
        this.result = value;
    }

    public static Wei ofWei(int value) {
        return ofWei(BigInteger.valueOf(value));
    }

    public static Wei ofWei(long value) {
        return ofWei(BigInteger.valueOf(value));
    }

    public static Wei ofWei(BigInteger value) {
        return new Wei(value);
    }

    public static Wei ofKwei(int value) {
        return ofKwei(BigInteger.valueOf(value));
    }

    public static Wei ofKwei(long value) {
        return ofKwei(BigInteger.valueOf(value));
    }

    public static Wei ofKwei(BigDecimal value) {
        return new Wei(value.multiply(KWEI_POW).toBigInteger());
    }

    public static Wei ofKwei(BigInteger value) {
        return new Wei(value.multiply(KWEI_POW.toBigInteger()));
    }

    public static Wei ofMwei(int value) {
        return ofMwei(BigInteger.valueOf(value));
    }

    public static Wei ofMwei(long value) {
        return ofMwei(BigInteger.valueOf(value));
    }

    public static Wei ofMwei(BigDecimal value) {
        return new Wei(value.multiply(MWEI_POW).toBigInteger());
    }

    public static Wei ofMwei(BigInteger value) {
        return new Wei(value.multiply(MWEI_POW.toBigInteger()));
    }

    public static Wei ofGwei(int value) {
        return ofGwei(BigInteger.valueOf(value));
    }

    public static Wei ofGwei(long value) {
        return ofGwei(BigInteger.valueOf(value));
    }

    public static Wei ofGwei(BigDecimal value) {
        return new Wei(value.multiply(GWEI_POW).toBigInteger());
    }

    public static Wei ofGwei(BigInteger value) {
        return new Wei(value.multiply(GWEI_POW.toBigInteger()));
    }

    public static Wei ofEther(int value) {
        return ofEther(BigInteger.valueOf(value));
    }

    public static Wei ofEther(long value) {
        return ofEther(BigInteger.valueOf(value));
    }

    public static Wei ofEther(BigDecimal value) {
        return new Wei(value.multiply(WEI_POW).toBigInteger());
    }

    public static Wei ofEther(BigInteger value) {
        return new Wei(value.multiply(WEI_POW.toBigInteger()));
    }

    public BigInteger asWei() {
        return result;
    }

    public BigDecimal asKwei() {
        return new BigDecimal(result).divide(KWEI_POW, RoundingMode.HALF_UP);
    }

    public BigDecimal asMwei() {
        return new BigDecimal(result).divide(MWEI_POW, RoundingMode.HALF_UP);
    }

    public BigDecimal asGwei() {
        return new BigDecimal(result).divide(GWEI_POW, RoundingMode.HALF_UP);
    }

    public BigDecimal asEther() {
        return new BigDecimal(result).divide(WEI_POW, RoundingMode.HALF_UP);
    }

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
    public int compareTo(@NotNull Wei o) {
        return result.compareTo(o.result);
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
