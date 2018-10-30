package io.api.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class TxToken extends BaseTx {

    private String nonce;
    private String blockHash;
    private String tokenName;
    private String tokenSymbol;
    private String tokenDecimal;
    private String transactionIndex;
    private String gasPrice;
    private String cumulativeGasUsed;
    private String confirmations;

    //<editor-fold desc="Getters">
    public String getNonce() {
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

    public String getTransactionIndex() {
        return transactionIndex;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public String getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public String getConfirmations() {
        return confirmations;
    }
    //</editor-fold>
}
