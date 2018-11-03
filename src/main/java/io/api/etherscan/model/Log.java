package io.api.etherscan.model;

import io.api.etherscan.util.BasicUtils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class Log {

    private String blockNumber;
    private long _blockNumber;
    private String address;
    private String transactionHash;
    private String transactionIndex;
    private long _transactionIndex;
    private String timeStamp;
    private LocalDateTime _timeStamp;
    private String data;
    private String gasPrice;
    private BigInteger _gasPrice;
    private String gasUsed;
    private BigInteger _gasUsed;
    private List<String> topics;
    private String logIndex;
    private long _logIndex;

    //<editor-fold desc="Getters">
    public long getBlockNumber() {
        if(!BasicUtils.isEmpty(blockNumber)){
            _blockNumber = BasicUtils.parseHex(blockNumber).longValue();
        }
        return _blockNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public long getTransactionIndex() {
        if(!BasicUtils.isEmpty(transactionIndex)){
            _transactionIndex = BasicUtils.parseHex(transactionIndex).longValue();
        }

        return _transactionIndex;
    }

    public LocalDateTime getTimeStamp() {
        if(_timeStamp == null && !BasicUtils.isEmpty(timeStamp)) {
            long formatted = (timeStamp.charAt(0) == '0' && timeStamp.charAt(1) == 'x')
                    ? BasicUtils.parseHex(timeStamp).longValue()
                    : Long.valueOf(timeStamp);
            _timeStamp = LocalDateTime.ofEpochSecond(formatted, 0, ZoneOffset.UTC);
        }
        return _timeStamp;
    }

    public String getData() {
        return data;
    }

    public BigInteger getGasPrice() {
        if(!BasicUtils.isEmpty(gasPrice)){
            _gasPrice = BasicUtils.parseHex(gasPrice);
        }

        return _gasPrice;
    }

    public BigInteger getGasUsed() {
        if(!BasicUtils.isEmpty(gasUsed)){
            _gasUsed = BasicUtils.parseHex(gasUsed);
        }

        return _gasUsed;
    }

    public List<String> getTopics() {
        return topics;
    }

    public long getLogIndex() {
        if(!BasicUtils.isEmpty(logIndex)){
            _logIndex = BasicUtils.parseHex(logIndex).longValue();
        }
        return _logIndex;
    }
    //</editor-fold>
}
