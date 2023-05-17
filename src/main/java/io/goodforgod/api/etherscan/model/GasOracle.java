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
    private BigInteger SafeGasPrice;
    private BigInteger ProposeGasPrice;
    private BigInteger FastGasPrice;
    private BigDecimal suggestBaseFee;
    private String gasUsedRatio;

    protected GasOracle() {}

    public Long getLastBlock() {
        return LastBlock;
    }

    public Wei getSafeGasPriceInWei() {
        return Wei.ofGwei(SafeGasPrice);
    }

    public Wei getProposeGasPriceInWei() {
        return Wei.ofGwei(ProposeGasPrice);
    }

    public Wei getFastGasPriceInWei() {
        return Wei.ofGwei(FastGasPrice);
    }

    public BigDecimal getSuggestBaseFee() {
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
        if (!(o instanceof GasOracle))
            return false;
        GasOracle gasOracle = (GasOracle) o;
        return Objects.equals(LastBlock, gasOracle.LastBlock) && Objects.equals(SafeGasPrice, gasOracle.SafeGasPrice)
                && Objects.equals(ProposeGasPrice, gasOracle.ProposeGasPrice)
                && Objects.equals(FastGasPrice, gasOracle.FastGasPrice)
                && Objects.equals(suggestBaseFee, gasOracle.suggestBaseFee)
                && Objects.equals(gasUsedRatio, gasOracle.gasUsedRatio);
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
                ", gasUsedRatio=" + gasUsedRatio +
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
        private BigDecimal suggestBaseFee;
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

        public GasOracleBuilder withSuggestBaseFee(BigDecimal suggestBaseFee) {
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
                gasOracle.ProposeGasPrice = this.proposeGasPrice.asGwei().toBigInteger();
            }
            if (this.safeGasPrice != null) {
                gasOracle.SafeGasPrice = this.safeGasPrice.asGwei().toBigInteger();
            }
            if (this.fastGasPrice != null) {
                gasOracle.FastGasPrice = this.fastGasPrice.asGwei().toBigInteger();
            }
            if (this.gasUsedRatio != null) {
                gasOracle.gasUsedRatio = this.gasUsedRatio.stream()
                        .map(BigDecimal::toString)
                        .collect(Collectors.joining(","));
            }
            return gasOracle;
        }
    }
}
