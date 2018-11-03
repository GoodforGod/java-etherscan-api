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
