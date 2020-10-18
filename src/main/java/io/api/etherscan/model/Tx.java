package io.api.etherscan.model;

import io.api.etherscan.util.BasicUtils;

import java.math.BigInteger;
import java.util.Objects;

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

    // <editor-fold desc="Getters">
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
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Tx tx = (Tx) o;

        if (nonce != tx.nonce)
            return false;
        if (transactionIndex != tx.transactionIndex)
            return false;
        if (!Objects.equals(blockHash, tx.blockHash))
            return false;
        return Objects.equals(isError, tx.isError);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (nonce ^ (nonce >>> 32));
        result = 31 * result + (blockHash != null ? blockHash.hashCode() : 0);
        result = 31 * result + transactionIndex;
        result = 31 * result + (isError != null ? isError.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tx{" +
                "nonce=" + nonce +
                ", blockHash='" + blockHash + '\'' +
                ", transactionIndex=" + transactionIndex +
                ", gasPrice=" + gasPrice +
                ", cumulativeGasUsed=" + cumulativeGasUsed +
                ", confirmations=" + confirmations +
                ", isError='" + isError + '\'' +
                ", txreceipt_status='" + txreceipt_status + '\'' +
                "} " + super.toString();
    }
}
