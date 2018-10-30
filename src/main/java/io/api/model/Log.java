package io.api.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class Log {

    private String blockNumber;
    private String address;
    private String transactionHash;
    private String transactionIndex;
    private String timeStamp;
    private LocalDateTime _timeStamp;
    private String data;
    private String gasPrice;
    private String gasUsed;
    private List<String> topics;
    private String logIndex;

    //<editor-fold desc="Getters">
    public String getBlockNumber() {
        return blockNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public String getTransactionIndex() {
        return transactionIndex;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public LocalDateTime get_timeStamp() {
        return _timeStamp;
    }

    public String getData() {
        return data;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public List<String> getTopics() {
        return topics;
    }

    public String getLogIndex() {
        return logIndex;
    }
    //</editor-fold>
}
