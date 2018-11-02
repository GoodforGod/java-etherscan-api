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

    public Uncle() {}

    public Uncle(String miner, BigInteger blockreward, int unclePosition) {
        this.miner = miner;
        this.blockreward = blockreward;
        this.unclePosition = unclePosition;
    }

    //<editor-fold desc="Getters">
    public String getMiner() {
        return miner;
    }

    public BigInteger getBlockreward() {
        return blockreward;
    }

    public int getUnclePosition() {
        return unclePosition;
    }
    //</editor-fold>
}
