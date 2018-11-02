package io.api.etherscan.model.proxy;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class TxProxy {

    private String to;
    private String hash;
    private String transactionIndex;
    private String from;
    private String gas;
    private String v;
    private String input;
    private String s;
    private String r;
    private String nonce;
    private String value;
    private String gasPrice;
    private String blockHash;
    private String blockNumber;

    //<editor-fold desc="Getters">
    public String getTo() {
        return to;
    }

    public String getHash() {
        return hash;
    }

    public String getTransactionIndex() {
        return transactionIndex;
    }

    public String getFrom() {
        return from;
    }

    public String getGas() {
        return gas;
    }

    public String getV() {
        return v;
    }

    public String getInput() {
        return input;
    }

    public String getS() {
        return s;
    }

    public String getR() {
        return r;
    }

    public String getNonce() {
        return nonce;
    }

    public String getValue() {
        return value;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getBlockNumber() {
        return blockNumber;
    }
    //</editor-fold>
}
