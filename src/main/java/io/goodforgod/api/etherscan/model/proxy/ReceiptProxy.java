package io.goodforgod.api.etherscan.model.proxy;

import com.google.gson.annotations.Expose;
import io.goodforgod.api.etherscan.model.Log;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.math.BigInteger;
import java.util.List;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ReceiptProxy {

    private String root;
    private String from;
    private String to;
    private String blockNumber;
    @Expose(serialize = false, deserialize = false)
    private Long _blockNumber;
    private String blockHash;
    private String transactionHash;
    private String transactionIndex;
    @Expose(serialize = false, deserialize = false)
    private Long _transactionIndex;
    private String gasUsed;
    @Expose(serialize = false, deserialize = false)
    private BigInteger _gasUsed;
    private String cumulativeGasUsed;
    @Expose(serialize = false, deserialize = false)
    private BigInteger _cumulativeGasUsed;
    private String contractAddress;

    private List<Log> logs;
    private String logsBloom;

    protected ReceiptProxy() {}

    // <editor-fold desc="Getters">
    public String getRoot() {
        return root;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Long getBlockNumber() {
        if (_blockNumber == null && !BasicUtils.isEmpty(blockNumber))
            _blockNumber = BasicUtils.parseHex(blockNumber).longValue();
        return _blockNumber;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public Long getTransactionIndex() {
        if (_transactionIndex == null && !BasicUtils.isEmpty(transactionIndex))
            _transactionIndex = BasicUtils.parseHex(transactionIndex).longValue();
        return _transactionIndex;
    }

    public BigInteger getGasUsed() {
        if (_gasUsed == null && !BasicUtils.isEmpty(gasUsed))
            _gasUsed = BasicUtils.parseHex(gasUsed);
        return _gasUsed;
    }

    public BigInteger getCumulativeGasUsed() {
        if (_cumulativeGasUsed == null && !BasicUtils.isEmpty(cumulativeGasUsed))
            _cumulativeGasUsed = BasicUtils.parseHex(cumulativeGasUsed);
        return _cumulativeGasUsed;
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
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ReceiptProxy that = (ReceiptProxy) o;

        if (blockNumber != null
                ? !blockNumber.equals(that.blockNumber)
                : that.blockNumber != null)
            return false;
        if (transactionHash != null
                ? !transactionHash.equals(that.transactionHash)
                : that.transactionHash != null)
            return false;
        return transactionIndex != null
                ? transactionIndex.equals(that.transactionIndex)
                : that.transactionIndex == null;
    }

    @Override
    public int hashCode() {
        int result = blockNumber != null
                ? blockNumber.hashCode()
                : 0;
        result = 31 * result + (transactionHash != null
                ? transactionHash.hashCode()
                : 0);
        result = 31 * result + (transactionIndex != null
                ? transactionIndex.hashCode()
                : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReceiptProxy{" +
                "root='" + root + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", blockNumber='" + blockNumber + '\'' +
                ", _blockNumber=" + _blockNumber +
                ", blockHash='" + blockHash + '\'' +
                ", transactionHash='" + transactionHash + '\'' +
                ", transactionIndex='" + transactionIndex + '\'' +
                ", _transactionIndex=" + _transactionIndex +
                ", gasUsed='" + gasUsed + '\'' +
                ", _gasUsed=" + _gasUsed +
                ", cumulativeGasUsed='" + cumulativeGasUsed + '\'' +
                ", _cumulativeGasUsed=" + _cumulativeGasUsed +
                ", contractAddress='" + contractAddress + '\'' +
                ", logs=" + logs +
                ", logsBloom='" + logsBloom + '\'' +
                '}';
    }

    public static ReceiptProxyBuilder builder() {
        return new ReceiptProxyBuilder();
    }

    public static final class ReceiptProxyBuilder {

        private String root;
        private String from;
        private String to;
        private Long blockNumber;
        private String blockHash;
        private String transactionHash;
        private Long transactionIndex;
        private BigInteger gasUsed;
        private BigInteger cumulativeGasUsed;
        private String contractAddress;
        private List<Log> logs;
        private String logsBloom;

        private ReceiptProxyBuilder() {}

        public ReceiptProxyBuilder withRoot(String root) {
            this.root = root;
            return this;
        }

        public ReceiptProxyBuilder withFrom(String from) {
            this.from = from;
            return this;
        }

        public ReceiptProxyBuilder withTo(String to) {
            this.to = to;
            return this;
        }

        public ReceiptProxyBuilder withBlockNumber(Long blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public ReceiptProxyBuilder withBlockHash(String blockHash) {
            this.blockHash = blockHash;
            return this;
        }

        public ReceiptProxyBuilder withTransactionHash(String transactionHash) {
            this.transactionHash = transactionHash;
            return this;
        }

        public ReceiptProxyBuilder withTransactionIndex(Long transactionIndex) {
            this.transactionIndex = transactionIndex;
            return this;
        }

        public ReceiptProxyBuilder withGasUsed(BigInteger gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public ReceiptProxyBuilder withCumulativeGasUsed(BigInteger cumulativeGasUsed) {
            this.cumulativeGasUsed = cumulativeGasUsed;
            return this;
        }

        public ReceiptProxyBuilder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public ReceiptProxyBuilder withLogs(List<Log> logs) {
            this.logs = logs;
            return this;
        }

        public ReceiptProxyBuilder withLogsBloom(String logsBloom) {
            this.logsBloom = logsBloom;
            return this;
        }

        public ReceiptProxy build() {
            ReceiptProxy receiptProxy = new ReceiptProxy();
            receiptProxy.logsBloom = this.logsBloom;
            receiptProxy.transactionHash = this.transactionHash;
            receiptProxy.blockNumber = String.valueOf(this.blockNumber);
            receiptProxy.from = this.from;
            receiptProxy._transactionIndex = this.transactionIndex;
            receiptProxy.blockHash = this.blockHash;
            receiptProxy.root = this.root;
            receiptProxy.contractAddress = this.contractAddress;
            receiptProxy.gasUsed = String.valueOf(this.gasUsed);
            receiptProxy._gasUsed = this.gasUsed;
            receiptProxy.logs = this.logs;
            receiptProxy.cumulativeGasUsed = String.valueOf(this.cumulativeGasUsed);
            receiptProxy.to = this.to;
            receiptProxy.transactionIndex = String.valueOf(this.transactionIndex);
            receiptProxy._blockNumber = this.blockNumber;
            receiptProxy._cumulativeGasUsed = this.cumulativeGasUsed;
            return receiptProxy;
        }
    }
}
