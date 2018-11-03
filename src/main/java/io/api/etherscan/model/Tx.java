package io.api.etherscan.model;

import io.api.etherscan.util.BasicUtils;

import java.math.BigInteger;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class Tx extends BaseTx {

    private long nonce;
    private String blockHash;
    private int transactionIndex;
    private BigInteger gasPrice;
    private BigInteger cumulativeGasUsed;
    private long confirmations;
    private String isError;
    private String txreceipt_status;

    //<editor-fold desc="Getters">
    public long getNonce() {
        return nonce;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public int getTransactionIndex() {
        return transactionIndex;
    }

    public BigInteger getGasPrice() {
        return gasPrice;
    }

    public boolean haveError() {
        return !BasicUtils.isEmpty(isError) && !isError.equals("0");
    }

    public String getTxreceipt_status() {
        return txreceipt_status;
    }

    public BigInteger getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public long getConfirmations() {
        return confirmations;
    }
    //</editor-fold>
}
