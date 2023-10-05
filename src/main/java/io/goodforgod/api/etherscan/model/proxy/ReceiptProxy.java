package io.goodforgod.api.etherscan.model.proxy;

import com.google.gson.annotations.Expose;
import io.goodforgod.api.etherscan.model.Log;
import io.goodforgod.api.etherscan.model.Wei;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.util.List;
import java.util.Objects;

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
    private Wei _gasUsed;
    private String cumulativeGasUsed;
    @Expose(serialize = false, deserialize = false)
    private Wei _cumulativeGasUsed;
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

    public Wei getGasUsed() {
        if (_gasUsed == null && !BasicUtils.isEmpty(gasUsed))
            _gasUsed = Wei.ofWei(BasicUtils.parseHex(gasUsed));
        return _gasUsed;
    }

    public Wei getGasUsedCumulative() {
        if (_cumulativeGasUsed == null && !BasicUtils.isEmpty(cumulativeGasUsed))
            _cumulativeGasUsed = Wei.ofWei(BasicUtils.parseHex(cumulativeGasUsed));
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
        if (!(o instanceof ReceiptProxy))
            return false;
        ReceiptProxy that = (ReceiptProxy) o;
        return Objects.equals(blockNumber, that.blockNumber) && Objects.equals(blockHash, that.blockHash)
                && Objects.equals(transactionHash, that.transactionHash)
                && Objects.equals(transactionIndex, that.transactionIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockNumber, blockHash, transactionHash, transactionIndex);
    }

    @Override
    public String toString() {
        return "ReceiptProxy{" +
                "root=" + root +
                ", from=" + from +
                ", to=" + to +
                ", blockNumber=" + blockNumber +
                ", blockHash=" + blockHash +
                ", transactionHash=" + transactionHash +
                ", transactionIndex=" + transactionIndex +
                ", gasUsed=" + gasUsed +
                ", cumulativeGasUsed=" + cumulativeGasUsed +
                ", contractAddress=" + contractAddress +
                ", logs=" + logs +
                ", logsBloom=" + logsBloom +
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
        private Wei gasUsed;
        private Wei cumulativeGasUsed;
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

        public ReceiptProxyBuilder withGasUsed(Wei gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public ReceiptProxyBuilder withCumulativeGasUsed(Wei cumulativeGasUsed) {
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
            if (this.gasUsed != null) {
                receiptProxy._gasUsed = this.gasUsed;
            }
            receiptProxy.logs = this.logs;
            receiptProxy.to = this.to;
            if (this.cumulativeGasUsed != null) {
                receiptProxy._cumulativeGasUsed = this.cumulativeGasUsed;
            }
            receiptProxy.transactionIndex = String.valueOf(this.transactionIndex);
            receiptProxy._blockNumber = this.blockNumber;
            return receiptProxy;
        }
    }
}
