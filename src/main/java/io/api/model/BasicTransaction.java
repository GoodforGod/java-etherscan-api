package io.api.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
abstract class BasicTransaction {

    private String blockNumber;
    private String blockHash;
    private String timeStamp;
    private String hash;
    private String nonce;
    private String confirmations;

    private String transactionIndex;
    private String from;
    private String to;
    private String value;

    private String gas;
    private String gasPrice;
    private String gasUsed;
    private String cumulativeGasUsed;
    private String input;
    private String contractAddress;

    BasicTransaction(String blockNumber, String blockHash, String timeStamp, String hash, String nonce, String confirmations,
                     String transactionIndex, String from, String to, String value,
                     String gas, String gasPrice, String gasUsed, String cumulativeGasUsed, String input, String contractAddress) {
        this.blockNumber = blockNumber;
        this.blockHash = blockHash;
        this.timeStamp = timeStamp;
        this.hash = hash;
        this.nonce = nonce;
        this.confirmations = confirmations;
        this.transactionIndex = transactionIndex;
        this.from = from;
        this.to = to;
        this.value = value;
        this.gas = gas;
        this.gasPrice = gasPrice;
        this.gasUsed = gasUsed;
        this.cumulativeGasUsed = cumulativeGasUsed;
        this.input = input;
        this.contractAddress = contractAddress;
    }

    //<editor-fold desc="Getters">
    public String getBlockNumber() {
        return blockNumber;
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

    public String getBlockHash() {
        return blockHash;
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

    public String getInput() {
        return input;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public String getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public String getConfirmations() {
        return confirmations;
    }
    //</editor-fold>
}
