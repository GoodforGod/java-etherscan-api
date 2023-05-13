package io.goodforgod.api.etherscan.model;

import io.goodforgod.api.etherscan.util.BasicUtils;
import java.math.BigInteger;
import java.util.List;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class BlockUncle extends Block {

    public static class Uncle {

        private String miner;
        private BigInteger blockreward;
        private int unclePosition;

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
            if (o == null || getClass() != o.getClass())
                return false;

            Uncle uncle = (Uncle) o;
            if (unclePosition != uncle.unclePosition)
                return false;
            if (miner != null
                    ? !miner.equals(uncle.miner)
                    : uncle.miner != null)
                return false;
            return blockreward != null
                    ? blockreward.equals(uncle.blockreward)
                    : uncle.blockreward == null;
        }

        @Override
        public int hashCode() {
            int result = miner != null
                    ? miner.hashCode()
                    : 0;
            result = 31 * result + (blockreward != null
                    ? blockreward.hashCode()
                    : 0);
            result = 31 * result + unclePosition;
            return result;
        }

        @Override
        public String toString() {
            return "Uncle{" +
                    "miner='" + miner + '\'' +
                    ", blockreward=" + blockreward +
                    ", unclePosition=" + unclePosition +
                    '}';
        }
    }

    private String blockMiner;
    private List<Uncle> uncles;
    private String uncleInclusionReward;

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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        BlockUncle that = (BlockUncle) o;

        return getBlockNumber() != 0 && getBlockNumber() == that.getBlockNumber();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = (int) (31 * result + getBlockNumber());
        return result;
    }

    @Override
    public String toString() {
        return "UncleBlock{" +
                "blockMiner='" + blockMiner + '\'' +
                ", uncles=" + uncles +
                ", uncleInclusionReward='" + uncleInclusionReward + '\'' +
                '}';
    }
}
