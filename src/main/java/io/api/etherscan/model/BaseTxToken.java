package io.api.etherscan.model;

public abstract class BaseTxToken extends BaseTx {

    private long nonce;
    private String blockHash;
    private String tokenName;
    private String tokenSymbol;
    private int transactionIndex;
    private long gasPrice;
    private long cumulativeGasUsed;
    private long confirmations;

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

    @Override
    public String toString() {
        return "BaseTxToken{" +
                "nonce=" + nonce +
                ", blockHash='" + blockHash + '\'' +
                ", tokenName='" + tokenName + '\'' +
                ", tokenSymbol='" + tokenSymbol + '\'' +
                ", transactionIndex=" + transactionIndex +
                ", gasPrice=" + gasPrice +
                ", cumulativeGasUsed=" + cumulativeGasUsed +
                ", confirmations=" + confirmations +
                '}' + super.toString();
    }
}
