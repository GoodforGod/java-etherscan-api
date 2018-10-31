package io.api.model.proxy;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class BlockProxy {

    private String number;
    private String hash;
    private String parentHash;
    private String stateRoot;
    private String size;
    private String difficulty;
    private String totalDifficulty;
    private String timestamp;

    private String miner;
    private String nonce;
    private String extraData;
    private String logsBloom;
    private String mixHash;
    private String gasUsed;
    private String gasLimit;

    private String sha3Uncles;
    private List<String> uncles;

    private String receiptsRoot;
    private String transactionsRoot;
    private List<TxProxy> transactions;

    //<editor-fold desc="Getters">
    public String getNumber() {
        return number;
    }

    public String getHash() {
        return hash;
    }

    public String getParentHash() {
        return parentHash;
    }

    public String getStateRoot() {
        return stateRoot;
    }

    public String getSize() {
        return size;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getTotalDifficulty() {
        return totalDifficulty;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMiner() {
        return miner;
    }

    public String getNonce() {
        return nonce;
    }

    public String getExtraData() {
        return extraData;
    }

    public String getLogsBloom() {
        return logsBloom;
    }

    public String getMixHash() {
        return mixHash;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public String getGasLimit() {
        return gasLimit;
    }

    public String getSha3Uncles() {
        return sha3Uncles;
    }

    public List<String> getUncles() {
        return uncles;
    }

    public String getReceiptsRoot() {
        return receiptsRoot;
    }

    public String getTransactionsRoot() {
        return transactionsRoot;
    }

    public List<TxProxy> getTransactions() {
        return transactions;
    }
    //</editor-fold>
}
