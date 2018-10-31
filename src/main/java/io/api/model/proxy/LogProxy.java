package io.api.model.proxy;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogProxy {

    private String blockNumber;
    private String blockHash;
    private String address;
    private String transactionIndex;
    private String transactionHash;
    private String data;
    private String removed;
    private String logIndex;
    private List<String> topics;

    //<editor-fold desc="Getters">
    public String getBlockNumber() {
        return blockNumber;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getAddress() {
        return address;
    }

    public String getTransactionIndex() {
        return transactionIndex;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public String getData() {
        return data;
    }

    public String getRemoved() {
        return removed;
    }

    public String getLogIndex() {
        return logIndex;
    }

    public List<String> getTopics() {
        return topics;
    }
    //</editor-fold>
}
