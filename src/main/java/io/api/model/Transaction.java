package io.api.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class Transaction {

    private String blockNumber;
    private String blockHash;
    private String timeStamp;
    private String hash;
    private String nonce;
    private String confirmations;

    private String txreceipt_status;
    private String transactionIndex;
    private String from;
    private String to;
    private String value;

    private String gas;
    private String gasPrice;
    private String gasUsed;
    private String cumulativeGasUsed;
    private boolean isError;
    private String input;
    private String contractAddress;

    //<editor-fold desc="Getters">
    public String getBlockNumber() {
        return blockNumber;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getHash() {
        return hash;
    }

    public String getNonce() {
        return nonce;
    }

    public String getConfirmations() {
        return confirmations;
    }

    public String getTxreceipt_status() {
        return txreceipt_status;
    }

    public String getTransactionIndex() {
        return transactionIndex;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getValue() {
        return value;
    }

    public String getGas() {
        return gas;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public String getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public boolean getIsError() {
        return isError;
    }

    public String getInput() {
        return input;
    }

    public String getContractAddress() {
        return contractAddress;
    }
    //</editor-fold>
}
