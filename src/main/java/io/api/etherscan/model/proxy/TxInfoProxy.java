package io.api.etherscan.model.proxy;

import io.api.etherscan.model.Log;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class TxInfoProxy {

    private String root;
    private String from;
    private String to;
    private String blockNumber;
    private String blockHash;
    private String transactionHash;
    private String transactionIndex;
    private String gasUsed;
    private String cumulativeGasUsed;
    private String contractAddress;

    private List<Log> logs;
    private String logsBloom;

    //<editor-fold desc="Getters">
    public String getRoot() {
        return root;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public String getTransactionIndex() {
        return transactionIndex;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public String getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public String getLogsBloom() {
        return logsBloom;
    }
    //</editor-fold>
}
