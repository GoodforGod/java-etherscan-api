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
    private Long _blockNumber;
    private String address;
    private String transactionHash;
    private String transactionIndex;
    private Long _transactionIndex;
    private String timeStamp;
    private LocalDateTime _timeStamp;
    private String data;
    private String gasPrice;
    private BigInteger _gasPrice;
    private String gasUsed;
    private BigInteger _gasUsed;
    private List<String> topics;
    private String logIndex;
    private Long _logIndex;

    //<editor-fold desc="Getters">
    public Long getBlockNumber() {
        if(_blockNumber == null && !BasicUtils.isEmpty(blockNumber)){
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

    public Long getTransactionIndex() {
        if(_transactionIndex == null && !BasicUtils.isEmpty(transactionIndex)){
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

    public Long getLogIndex() {
        if(_logIndex == null && !BasicUtils.isEmpty(logIndex)){
            _logIndex = BasicUtils.parseHex(logIndex).longValue();
        }
        return _logIndex;
    }
    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (blockNumber != null ? !blockNumber.equals(log.blockNumber) : log.blockNumber != null) return false;
        if (address != null ? !address.equals(log.address) : log.address != null) return false;
        if (transactionHash != null ? !transactionHash.equals(log.transactionHash) : log.transactionHash != null)
            return false;
        if (timeStamp != null ? !timeStamp.equals(log.timeStamp) : log.timeStamp != null) return false;
        return logIndex != null ? logIndex.equals(log.logIndex) : log.logIndex == null;
    }

    @Override
    public int hashCode() {
        int result = blockNumber != null ? blockNumber.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (transactionHash != null ? transactionHash.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (logIndex != null ? logIndex.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Log{" +
                "blockNumber='" + blockNumber + '\'' +
                ", _blockNumber=" + _blockNumber +
                ", address='" + address + '\'' +
                ", transactionHash='" + transactionHash + '\'' +
                ", transactionIndex='" + transactionIndex + '\'' +
                ", _transactionIndex=" + _transactionIndex +
                ", timeStamp='" + timeStamp + '\'' +
                ", _timeStamp=" + _timeStamp +
                ", data='" + data + '\'' +
                ", gasPrice='" + gasPrice + '\'' +
                ", _gasPrice=" + _gasPrice +
                ", gasUsed='" + gasUsed + '\'' +
                ", _gasUsed=" + _gasUsed +
                ", topics=" + topics +
                ", logIndex='" + logIndex + '\'' +
                ", _logIndex=" + _logIndex +
                '}';
    }
}
