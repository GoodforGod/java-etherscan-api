package io.api.etherscan.model.proxy;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class ReceiptProxy {

    private String blockNumber;
    private String blockHash;
    private String root;
    private String from;
    private String to;
    private String transactionHash;
    private String transactionIndex;
    private String cumulativeGasUsed;
    private String gasUsed;
    private String contractAddress;
    private String logsBloom;
    private List<LogProxy> logs;

    //<editor-fold desc="Getters">
    public String getBlockNumber() {
        return blockNumber;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getRoot() {
        return root;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public String getTransactionIndex() {
        return transactionIndex;
    }

    public String getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public String getLogsBloom() {
        return logsBloom;
    }

    public List<LogProxy> getLogs() {
        return logs;
    }
    //</editor-fold>
}
