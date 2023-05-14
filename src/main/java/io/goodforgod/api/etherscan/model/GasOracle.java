package io.goodforgod.api.etherscan.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
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

    protected GasOracle() {}

    public Long getLastBlock() {
        return LastBlock;
    }

    public Wei getSafeGasPriceInWei() {
        return new Wei(BigInteger.valueOf(SafeGasPrice).multiply(BigInteger.TEN.pow(9)));
    }

    public Wei getProposeGasPriceInWei() {
        return new Wei(BigInteger.valueOf(ProposeGasPrice).multiply(BigInteger.TEN.pow(9)));
    }

    public Wei getFastGasPriceInWei() {
        return new Wei(BigInteger.valueOf(FastGasPrice).multiply(BigInteger.TEN.pow(9)));
    }

    public Double getSuggestBaseFee() {
        return suggestBaseFee;
    }

    public List<BigDecimal> getGasUsedRatio() {
        return Arrays.stream(gasUsedRatio.split(","))
                .map(BigDecimal::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GasOracle gasOracle = (GasOracle) o;
        return LastBlock.equals(gasOracle.LastBlock) && SafeGasPrice.equals(gasOracle.SafeGasPrice)
                && ProposeGasPrice.equals(gasOracle.ProposeGasPrice) && FastGasPrice.equals(gasOracle.FastGasPrice)
                && suggestBaseFee.equals(gasOracle.suggestBaseFee) && gasUsedRatio.equals(gasOracle.gasUsedRatio);
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

    public static GasOracleBuilder builder() {
        return new GasOracleBuilder();
    }

    public static final class GasOracleBuilder {

        private Long lastBlock;
        private Wei safeGasPrice;
        private Wei proposeGasPrice;
        private Wei fastGasPrice;
        private Double suggestBaseFee;
        private List<BigDecimal> gasUsedRatio;

        private GasOracleBuilder() {}

        public GasOracleBuilder withLastBlock(Long lastBlock) {
            this.lastBlock = lastBlock;
            return this;
        }

        public GasOracleBuilder withSafeGasPrice(Wei safeGasPrice) {
            this.safeGasPrice = safeGasPrice;
            return this;
        }

        public GasOracleBuilder withProposeGasPrice(Wei proposeGasPrice) {
            this.proposeGasPrice = proposeGasPrice;
            return this;
        }

        public GasOracleBuilder withFastGasPrice(Wei fastGasPrice) {
            this.fastGasPrice = fastGasPrice;
            return this;
        }

        public GasOracleBuilder withSuggestBaseFee(Double suggestBaseFee) {
            this.suggestBaseFee = suggestBaseFee;
            return this;
        }

        public GasOracleBuilder withGasUsedRatio(List<BigDecimal> gasUsedRatio) {
            this.gasUsedRatio = gasUsedRatio;
            return this;
        }

        public GasOracle build() {
            GasOracle gasOracle = new GasOracle();
            gasOracle.LastBlock = this.lastBlock;
            gasOracle.suggestBaseFee = this.suggestBaseFee;
            if (this.proposeGasPrice != null) {
                gasOracle.ProposeGasPrice = this.proposeGasPrice.asGwei().intValue();
            }
            if (this.safeGasPrice != null) {
                gasOracle.SafeGasPrice = this.safeGasPrice.asGwei().intValue();
            }
            if (this.fastGasPrice != null) {
                gasOracle.FastGasPrice = this.fastGasPrice.asGwei().intValue();
            }
            if (this.gasUsedRatio != null) {
                gasOracle.gasUsedRatio = this.gasUsedRatio.stream()
                        .map(BigDecimal::toString)
                        .collect(Collectors.joining(", "));
            }
            return gasOracle;
        }
    }
}
