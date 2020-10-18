package io.api.etherscan.model;

import io.api.etherscan.util.BasicUtils;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class UncleBlock extends Block {

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

        UncleBlock that = (UncleBlock) o;

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
