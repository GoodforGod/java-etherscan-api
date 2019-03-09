package io.api.etherscan.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class TxToken extends BaseTx {

    private long nonce;
    private String blockHash;
    private String tokenName;
    private String tokenSymbol;
    private String tokenDecimal;
    private int transactionIndex;
    private long gasPrice;
    private long cumulativeGasUsed;
    private long confirmations;

    //<editor-fold desc="Getters">
    public long getNonce() {
        return nonce;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getTokenName() {
        return tokenName;
    }

    public String getTokenSymbol() {
        return tokenSymbol;
    }

    public String getTokenDecimal() {
        return tokenDecimal;
    }

    public int getTransactionIndex() {
        return transactionIndex;
    }

    public long getGasPrice() {
        return gasPrice;
    }

    public long getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public long getConfirmations() {
        return confirmations;
    }
    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TxToken txToken = (TxToken) o;

        if (nonce != txToken.nonce) return false;
        if (transactionIndex != txToken.transactionIndex) return false;
        if (gasPrice != txToken.gasPrice) return false;
        if (cumulativeGasUsed != txToken.cumulativeGasUsed) return false;
        if (confirmations != txToken.confirmations) return false;
        if (blockHash != null ? !blockHash.equals(txToken.blockHash) : txToken.blockHash != null) return false;
        if (tokenName != null ? !tokenName.equals(txToken.tokenName) : txToken.tokenName != null) return false;
        if (tokenSymbol != null ? !tokenSymbol.equals(txToken.tokenSymbol) : txToken.tokenSymbol != null) return false;
        return tokenDecimal != null ? tokenDecimal.equals(txToken.tokenDecimal) : txToken.tokenDecimal == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (nonce ^ (nonce >>> 32));
        result = 31 * result + (blockHash != null ? blockHash.hashCode() : 0);
        result = 31 * result + (tokenName != null ? tokenName.hashCode() : 0);
        result = 31 * result + (tokenSymbol != null ? tokenSymbol.hashCode() : 0);
        result = 31 * result + (tokenDecimal != null ? tokenDecimal.hashCode() : 0);
        result = 31 * result + transactionIndex;
        result = 31 * result + (int) (gasPrice ^ (gasPrice >>> 32));
        result = 31 * result + (int) (cumulativeGasUsed ^ (cumulativeGasUsed >>> 32));
        result = 31 * result + (int) (confirmations ^ (confirmations >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TxToken{" +
                "nonce=" + nonce +
                ", blockHash='" + blockHash + '\'' +
                ", tokenName='" + tokenName + '\'' +
                ", tokenSymbol='" + tokenSymbol + '\'' +
                ", tokenDecimal='" + tokenDecimal + '\'' +
                ", transactionIndex=" + transactionIndex +
                ", gasPrice=" + gasPrice +
                ", cumulativeGasUsed=" + cumulativeGasUsed +
                ", confirmations=" + confirmations +
                "} " + super.toString();
    }
}
