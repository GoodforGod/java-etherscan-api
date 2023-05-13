package io.api.etherscan.model;

import java.math.BigInteger;
import java.util.Objects;

/**
 * ! NO DESCRIPTION !
 *
 * @author Abhay Gupta
 * @since 14.11.2022
 */
public class GasOracle {
    private Long LastBlock;
    private Integer SafeGasPrice;
    private Integer ProposeGasPrice;
    private Integer FastGasPrice;
    private Double suggestBaseFee;
    private String gasUsedRatio;

    public Long getLastBlock() {
        return LastBlock;
    }

    public BigInteger getSafeGasPriceInWei() {
        return BigInteger.valueOf(SafeGasPrice).multiply(BigInteger.TEN.pow(9));
    }

    public BigInteger getProposeGasPriceInWei() {
        return BigInteger.valueOf(ProposeGasPrice).multiply(BigInteger.TEN.pow(9));
    }

    public BigInteger getFastGasPriceInWei() {
        return BigInteger.valueOf(FastGasPrice).multiply(BigInteger.TEN.pow(9));
    }

    public Double getSuggestBaseFee() {
        return suggestBaseFee;
    }

    public String getGasUsedRatio() {
        return gasUsedRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GasOracle gasOracle = (GasOracle) o;
        return LastBlock.equals(gasOracle.LastBlock) && SafeGasPrice.equals(gasOracle.SafeGasPrice) && ProposeGasPrice.equals(gasOracle.ProposeGasPrice) && FastGasPrice.equals(gasOracle.FastGasPrice) && suggestBaseFee.equals(gasOracle.suggestBaseFee) && gasUsedRatio.equals(gasOracle.gasUsedRatio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(LastBlock, SafeGasPrice, ProposeGasPrice, FastGasPrice, suggestBaseFee, gasUsedRatio);
    }

    @Override
    public String toString() {
        return "GasOracle{" +
                "LastBlock=" + LastBlock +
                ", SafeGasPrice=" + SafeGasPrice +
                ", ProposeGasPrice=" + ProposeGasPrice +
                ", FastGasPrice=" + FastGasPrice +
                ", suggestBaseFee=" + suggestBaseFee +
                ", gasUsedRatio='" + gasUsedRatio + '\'' +
                '}';
    }
}
