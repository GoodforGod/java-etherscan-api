package io.goodforgod.api.etherscan.model;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Please Add Description Here.
 *
 * @author Anton Kurako (GoodforGod)
 * @since 14.05.2023
 */
public class EthSupply {

    private String EthSupply;
    private String Eth2Staking;
    private String BurntFees;
    private String WithdrawnTotal;

    public Wei getEthSupply() {
        return Wei.ofWei(new BigInteger(EthSupply));
    }

    public Wei getEth2Staking() {
        return Wei.ofWei(new BigInteger(Eth2Staking));
    }

    public Wei getBurntFees() {
        return Wei.ofWei(new BigInteger(BurntFees));
    }

    public Wei getTotal() {
        final BigInteger total = getEthSupply().asWei()
                .add(getEth2Staking().asWei())
                .min(getBurntFees().asWei());
        return Wei.ofWei(total);
    }

    public Wei getWithdrawnTotal() {
        return Wei.ofWei(new BigInteger(WithdrawnTotal));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EthSupply))
            return false;
        EthSupply ethSupply = (EthSupply) o;
        return Objects.equals(EthSupply, ethSupply.EthSupply) && Objects.equals(Eth2Staking, ethSupply.Eth2Staking)
                && Objects.equals(BurntFees, ethSupply.BurntFees) && Objects.equals(WithdrawnTotal, ethSupply.WithdrawnTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EthSupply, Eth2Staking, BurntFees, WithdrawnTotal);
    }

    @Override
    public String toString() {
        return "EthSupply{" +
                "EthSupply='" + EthSupply + '\'' +
                ", Eth2Staking='" + Eth2Staking + '\'' +
                ", BurntFees='" + BurntFees + '\'' +
                ", WithdrawnTotal='" + WithdrawnTotal + '\'' +
                '}';
    }

    public static EthSupplyBuilder builder() {
        return new EthSupplyBuilder();
    }

    public static final class EthSupplyBuilder {

        private Wei ethSupply;
        private Wei eth2Staking;
        private Wei burntFees;
        private Wei withdrawnTotal;

        private EthSupplyBuilder() {}

        public EthSupplyBuilder withEthSupply(Wei ethSupply) {
            this.ethSupply = ethSupply;
            return this;
        }

        public EthSupplyBuilder withEth2Staking(Wei eth2Staking) {
            this.eth2Staking = eth2Staking;
            return this;
        }

        public EthSupplyBuilder withBurntFees(Wei burntFees) {
            this.burntFees = burntFees;
            return this;
        }

        public EthSupplyBuilder withWithdrawnTotal(Wei withdrawnTotal) {
            this.withdrawnTotal = withdrawnTotal;
            return this;
        }

        public EthSupply build() {
            EthSupply ethSupply = new EthSupply();
            ethSupply.BurntFees = this.burntFees.toString();
            ethSupply.Eth2Staking = this.eth2Staking.toString();
            ethSupply.EthSupply = this.ethSupply.toString();
            ethSupply.WithdrawnTotal = this.withdrawnTotal.toString();
            return ethSupply;
        }
    }
}
