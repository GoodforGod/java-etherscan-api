package io.goodforgod.api.etherscan.model;

import io.goodforgod.api.etherscan.util.BasicUtils;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class BlockUncle extends Block {

    public static class Uncle {

        private String miner;
        private BigInteger blockreward;
        private int unclePosition;

        private Uncle() {}

        // <editor-fold desc="Getters">
        public String getMiner() {
            return miner;
        }

        public BigInteger getBlockreward() {
            return blockreward;
        }

        public int getUnclePosition() {
            return unclePosition;
        }
        // </editor-fold>

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Uncle))
                return false;
            Uncle uncle = (Uncle) o;
            return unclePosition == uncle.unclePosition && Objects.equals(miner, uncle.miner)
                    && Objects.equals(blockreward, uncle.blockreward);
        }

        @Override
        public int hashCode() {
            return Objects.hash(miner, blockreward, unclePosition);
        }

        @Override
        public String toString() {
            return "Uncle{" +
                    "miner='" + miner + '\'' +
                    ", blockreward=" + blockreward +
                    ", unclePosition=" + unclePosition +
                    '}';
        }

        public static UncleBuilder builder() {
            return new UncleBuilder();
        }

        public static final class UncleBuilder {

            private String miner;
            private BigInteger blockreward;
            private int unclePosition;

            private UncleBuilder() {}

            public UncleBuilder withMiner(String miner) {
                this.miner = miner;
                return this;
            }

            public UncleBuilder withBlockreward(BigInteger blockreward) {
                this.blockreward = blockreward;
                return this;
            }

            public UncleBuilder withUnclePosition(int unclePosition) {
                this.unclePosition = unclePosition;
                return this;
            }

            public Uncle build() {
                Uncle uncle = new Uncle();
                uncle.miner = this.miner;
                uncle.blockreward = this.blockreward;
                uncle.unclePosition = this.unclePosition;
                return uncle;
            }
        }
    }

    private String blockMiner;
    private List<Uncle> uncles;
    private String uncleInclusionReward;

    protected BlockUncle() {
        super();
    }

    // <editor-fold desc="Getters">
    public boolean isEmpty() {
        return getBlockNumber() == 0 && getBlockReward() == null
                && getTimeStamp() == null
                && BasicUtils.isEmpty(blockMiner);
    }

    public String getBlockMiner() {
        return blockMiner;
    }

    public List<Uncle> getUncles() {
        return uncles;
    }

    public String getUncleInclusionReward() {
        return uncleInclusionReward;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "UncleBlock{" +
                "blockMiner='" + blockMiner + '\'' +
                ", uncles=" + uncles +
                ", uncleInclusionReward='" + uncleInclusionReward + '\'' +
                '}';
    }

    public static BlockUncleBuilder builder() {
        return new BlockUncleBuilder();
    }

    public static final class BlockUncleBuilder extends Block.BlockBuilder {

        private long blockNumber;
        private BigInteger blockReward;
        private LocalDateTime timeStamp;
        private String blockMiner;
        private List<Uncle> uncles;
        private String uncleInclusionReward;

        private BlockUncleBuilder() {
            super();
        }

        public BlockUncleBuilder withBlockNumber(long blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public BlockUncleBuilder withBlockReward(BigInteger blockReward) {
            this.blockReward = blockReward;
            return this;
        }

        public BlockUncleBuilder withTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public BlockUncleBuilder withBlockMiner(String blockMiner) {
            this.blockMiner = blockMiner;
            return this;
        }

        public BlockUncleBuilder withUncles(List<Uncle> uncles) {
            this.uncles = uncles;
            return this;
        }

        public BlockUncleBuilder withUncleInclusionReward(String uncleInclusionReward) {
            this.uncleInclusionReward = uncleInclusionReward;
            return this;
        }

        public BlockUncle build() {
            BlockUncle blockUncle = new BlockUncle();
            blockUncle.uncles = this.uncles;
            blockUncle.uncleInclusionReward = this.uncleInclusionReward;
            blockUncle.blockNumber = this.blockNumber;
            blockUncle.blockReward = this.blockReward;
            blockUncle.blockMiner = this.blockMiner;
            if (this.timeStamp != null) {
                blockUncle._timeStamp = this.timeStamp;
                blockUncle.timeStamp = String.valueOf(this.timeStamp.toEpochSecond(ZoneOffset.UTC));
            }
            return blockUncle;
        }
    }
}
