package io.goodforgod.api.etherscan.model;

import com.google.gson.annotations.Expose;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 31.10.2018
 */
public class Log {

    private String blockNumber;
    @Expose(deserialize = false, serialize = false)
    private Long _blockNumber;
    private String address;
    private String transactionHash;
    private String transactionIndex;
    @Expose(deserialize = false, serialize = false)
    private Long _transactionIndex;
    private String timeStamp;
    @Expose(deserialize = false, serialize = false)
    private LocalDateTime _timeStamp;
    private String data;
    private String gasPrice;
    @Expose(deserialize = false, serialize = false)
    private Wei _gasPrice;
    private String gasUsed;
    @Expose(deserialize = false, serialize = false)
    private Wei _gasUsed;
    private List<String> topics;
    private String logIndex;
    @Expose(deserialize = false, serialize = false)
    private Long _logIndex;

    protected Log() {}

    // <editor-fold desc="Getters">
    public Long getBlockNumber() {
        if (_blockNumber == null && !BasicUtils.isEmpty(blockNumber)) {
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
        if (_transactionIndex == null && !BasicUtils.isEmpty(transactionIndex)) {
            _transactionIndex = BasicUtils.parseHex(transactionIndex).longValue();
        }

        return _transactionIndex;
    }

    public LocalDateTime getTimeStamp() {
        if (_timeStamp == null && !BasicUtils.isEmpty(timeStamp)) {
            long formatted = getTimeStampAsSeconds();
            _timeStamp = LocalDateTime.ofEpochSecond(formatted, 0, ZoneOffset.UTC);
        }
        return _timeStamp;
    }

    /**
     * Return the "timeStamp" field of the event record as a long-int representing the seconds
     * since the Unix epoch (1970-01-01 00:00:00).
     *
     * @return milliseconds between Unix epoch and `timeStamp`. If field is empty or null, returns null
     */
    public Long getTimeStampAsSeconds() {
        if (BasicUtils.isEmpty(timeStamp)) {
            return null;
        }

        return (timeStamp.charAt(0) == '0' && timeStamp.charAt(1) == 'x')
                ? BasicUtils.parseHex(timeStamp).longValue()
                : Long.parseLong(timeStamp);
    }

    public String getData() {
        return data;
    }

    public Wei getGasPrice() {
        if (!BasicUtils.isEmpty(gasPrice)) {
            _gasPrice = Wei.ofWei(BasicUtils.parseHex(gasPrice));
        }

        return _gasPrice;
    }

    public Wei getGasUsed() {
        if (!BasicUtils.isEmpty(gasUsed)) {
            _gasUsed = Wei.ofWei(BasicUtils.parseHex(gasUsed));
        }

        return _gasUsed;
    }

    public List<String> getTopics() {
        return topics;
    }

    public Long getLogIndex() {
        if (_logIndex == null && !BasicUtils.isEmpty(logIndex)) {
            _logIndex = BasicUtils.parseHex(logIndex).longValue();
        }
        return _logIndex;
    }
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Log))
            return false;
        Log log = (Log) o;
        return Objects.equals(blockNumber, log.blockNumber) && Objects.equals(address, log.address)
                && Objects.equals(transactionHash, log.transactionHash) && Objects.equals(transactionIndex, log.transactionIndex)
                && Objects.equals(logIndex, log.logIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockNumber, address, transactionHash, transactionIndex, logIndex);
    }

    @Override
    public String toString() {
        return "Log{" +
                "blockNumber=" + blockNumber +
                ", address=" + address +
                ", transactionHash=" + transactionHash +
                ", transactionIndex=" + transactionIndex +
                ", timeStamp=" + timeStamp +
                ", data=" + data +
                ", gasPrice=" + gasPrice +
                ", gasUsed=" + gasUsed +
                ", topics=" + topics +
                ", logIndex=" + logIndex +
                '}';
    }

    public static LogBuilder builder() {
        return new LogBuilder();
    }

    public static class LogBuilder {

        private Long blockNumber;
        private String address;
        private String transactionHash;
        private Long transactionIndex;
        private LocalDateTime timeStamp;
        private String data;
        private Wei gasPrice;
        private Wei gasUsed;
        private List<String> topics;
        private Long logIndex;

        private LogBuilder() {}

        public LogBuilder withBlockNumber(Long blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public LogBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public LogBuilder withTransactionHash(String transactionHash) {
            this.transactionHash = transactionHash;
            return this;
        }

        public LogBuilder withTransactionIndex(Long transactionIndex) {
            this.transactionIndex = transactionIndex;
            return this;
        }

        public LogBuilder withTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public LogBuilder withData(String data) {
            this.data = data;
            return this;
        }

        public LogBuilder withGasPrice(Wei gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public LogBuilder withGasUsed(Wei gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public LogBuilder withTopics(List<String> topics) {
            this.topics = topics;
            return this;
        }

        public LogBuilder withLogIndex(Long logIndex) {
            this.logIndex = logIndex;
            return this;
        }

        public Log build() {
            Log log = new Log();
            log.address = this.address;
            if (this.gasPrice != null) {
                log._gasPrice = this.gasPrice;
            }
            log._logIndex = this.logIndex;
            log._transactionIndex = this.transactionIndex;
            log.blockNumber = String.valueOf(this.blockNumber);
            log.transactionIndex = String.valueOf(this.transactionIndex);
            if (this.timeStamp != null) {
                log.timeStamp = String.valueOf(this.timeStamp.toEpochSecond(ZoneOffset.UTC));
                log._timeStamp = this.timeStamp;
            }
            log.data = this.data;
            if (this.gasUsed != null) {
                log._gasUsed = this.gasUsed;
            }
            log.logIndex = String.valueOf(this.logIndex);
            log._blockNumber = this.blockNumber;
            log.topics = this.topics;
            log.transactionHash = this.transactionHash;
            return log;
        }
    }
}
