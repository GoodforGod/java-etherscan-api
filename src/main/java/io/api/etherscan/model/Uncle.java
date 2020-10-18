package io.api.etherscan.model;

import java.math.BigInteger;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Uncle {

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
        if (miner != null ? !miner.equals(uncle.miner) : uncle.miner != null)
            return false;
        return blockreward != null ? blockreward.equals(uncle.blockreward) : uncle.blockreward == null;
    }

    @Override
    public int hashCode() {
        int result = miner != null ? miner.hashCode() : 0;
        result = 31 * result + (blockreward != null ? blockreward.hashCode() : 0);
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
