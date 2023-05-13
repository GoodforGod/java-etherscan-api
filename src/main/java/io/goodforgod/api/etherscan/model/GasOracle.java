package io.goodforgod.api.etherscan.model;

import java.math.BigInteger;
import java.util.Objects;

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

        private Long LastBlock;
        private Integer SafeGasPrice;
        private Integer ProposeGasPrice;
        private Integer FastGasPrice;
        private Double suggestBaseFee;
        private String gasUsedRatio;

        private GasOracleBuilder() {}

        public GasOracleBuilder withLastBlock(Long LastBlock) {
            this.LastBlock = LastBlock;
            return this;
        }

        public GasOracleBuilder withSafeGasPrice(Integer SafeGasPrice) {
            this.SafeGasPrice = SafeGasPrice;
            return this;
        }

        public GasOracleBuilder withProposeGasPrice(Integer ProposeGasPrice) {
            this.ProposeGasPrice = ProposeGasPrice;
            return this;
        }

        public GasOracleBuilder withFastGasPrice(Integer FastGasPrice) {
            this.FastGasPrice = FastGasPrice;
            return this;
        }

        public GasOracleBuilder withSuggestBaseFee(Double suggestBaseFee) {
            this.suggestBaseFee = suggestBaseFee;
            return this;
        }

        public GasOracleBuilder withGasUsedRatio(String gasUsedRatio) {
            this.gasUsedRatio = gasUsedRatio;
            return this;
        }

        public GasOracle build() {
            GasOracle gasOracle = new GasOracle();
            gasOracle.ProposeGasPrice = this.ProposeGasPrice;
            gasOracle.LastBlock = this.LastBlock;
            gasOracle.suggestBaseFee = this.suggestBaseFee;
            gasOracle.SafeGasPrice = this.SafeGasPrice;
            gasOracle.FastGasPrice = this.FastGasPrice;
            gasOracle.gasUsedRatio = this.gasUsedRatio;
            return gasOracle;
        }
    }
}
