package io.goodforgod.api.etherscan.model.proxy;

import com.google.gson.annotations.Expose;
import io.goodforgod.api.etherscan.model.Wei;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * @author GoodforGod
 * @since 31.10.2018
 */
public class BlockProxy {

    private String number;
    @Expose(deserialize = false, serialize = false)
    private Long _number;
    private String hash;
    private String parentHash;
    private String stateRoot;
    private String size;
    @Expose(deserialize = false, serialize = false)
    private Long _size;
    private String difficulty;
    private String totalDifficulty;
    private String timestamp;
    @Expose(deserialize = false, serialize = false)
    private LocalDateTime _timestamp;

    private String miner;
    private String nonce;
    private String extraData;
    private String logsBloom;
    private String mixHash;
    private String gasUsed;
    @Expose(deserialize = false, serialize = false)
    private Wei _gasUsed;
    private String gasLimit;
    @Expose(deserialize = false, serialize = false)
    private Wei _gasLimit;

    private String sha3Uncles;
    private List<String> uncles;

    private String receiptsRoot;
    private String transactionsRoot;
    private List<TxProxy> transactions;

    protected BlockProxy() {}

    // <editor-fold desc="Getters">
    public Long getNumber() {
        if (_number == null && !BasicUtils.isEmpty(number))
            _number = BasicUtils.parseHex(number).longValue();
        return _number;
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

    public Long getSize() {
        if (_size == null && !BasicUtils.isEmpty(size))
            _size = BasicUtils.parseHex(size).longValue();
        return _size;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getTotalDifficulty() {
        return totalDifficulty;
    }

    public LocalDateTime getTimeStamp() {
        if (_timestamp == null && !BasicUtils.isEmpty(timestamp))
            _timestamp = LocalDateTime.ofEpochSecond(BasicUtils.parseHex(timestamp).longValue(), 0, ZoneOffset.UTC);
        return _timestamp;
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

    public Wei getGasUsed() {
        if (_gasUsed == null && !BasicUtils.isEmpty(gasUsed))
            _gasUsed = Wei.ofWei(BasicUtils.parseHex(gasUsed));
        return _gasUsed;
    }

    public Wei getGasLimit() {
        if (_gasLimit == null && !BasicUtils.isEmpty(gasLimit))
            _gasLimit = Wei.ofWei(BasicUtils.parseHex(gasLimit));
        return _gasLimit;
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
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BlockProxy that = (BlockProxy) o;

        if (number != null
                ? !number.equals(that.number)
                : that.number != null)
            return false;
        if (hash != null
                ? !hash.equals(that.hash)
                : that.hash != null)
            return false;
        return parentHash != null
                ? parentHash.equals(that.parentHash)
                : that.parentHash == null;
    }

    @Override
    public int hashCode() {
        int result = number != null
                ? number.hashCode()
                : 0;
        result = 31 * result + (hash != null
                ? hash.hashCode()
                : 0);
        result = 31 * result + (parentHash != null
                ? parentHash.hashCode()
                : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlockProxy{" +
                "number='" + number + '\'' +
                ", _number=" + _number +
                ", hash='" + hash + '\'' +
                ", parentHash='" + parentHash + '\'' +
                ", stateRoot='" + stateRoot + '\'' +
                ", size='" + size + '\'' +
                ", _size=" + _size +
                ", difficulty='" + difficulty + '\'' +
                ", totalDifficulty='" + totalDifficulty + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", _timestamp=" + _timestamp +
                ", miner='" + miner + '\'' +
                ", nonce='" + nonce + '\'' +
                ", extraData='" + extraData + '\'' +
                ", logsBloom='" + logsBloom + '\'' +
                ", mixHash='" + mixHash + '\'' +
                ", gasUsed='" + gasUsed + '\'' +
                ", _gasUsed=" + _gasUsed +
                ", gasLimit='" + gasLimit + '\'' +
                ", _gasLimit=" + _gasLimit +
                ", sha3Uncles='" + sha3Uncles + '\'' +
                ", uncles=" + uncles +
                ", receiptsRoot='" + receiptsRoot + '\'' +
                ", transactionsRoot='" + transactionsRoot + '\'' +
                ", transactions=" + transactions +
                '}';
    }

    public static BlockProxyBuilder builder() {
        return new BlockProxyBuilder();
    }

    public static final class BlockProxyBuilder {

        private Long number;
        private String hash;
        private String parentHash;
        private String stateRoot;
        private Long size;
        private String difficulty;
        private String totalDifficulty;
        private LocalDateTime timestamp;
        private String miner;
        private String nonce;
        private String extraData;
        private String logsBloom;
        private String mixHash;
        private Wei gasUsed;
        private Wei gasLimit;
        private String sha3Uncles;
        private List<String> uncles;
        private String receiptsRoot;
        private String transactionsRoot;
        private List<TxProxy> transactions;

        private BlockProxyBuilder() {}

        public BlockProxyBuilder withNumber(Long number) {
            this.number = number;
            return this;
        }

        public BlockProxyBuilder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public BlockProxyBuilder withParentHash(String parentHash) {
            this.parentHash = parentHash;
            return this;
        }

        public BlockProxyBuilder withStateRoot(String stateRoot) {
            this.stateRoot = stateRoot;
            return this;
        }

        public BlockProxyBuilder withSize(Long size) {
            this.size = size;
            return this;
        }

        public BlockProxyBuilder withDifficulty(String difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public BlockProxyBuilder withTotalDifficulty(String totalDifficulty) {
            this.totalDifficulty = totalDifficulty;
            return this;
        }

        public BlockProxyBuilder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public BlockProxyBuilder withMiner(String miner) {
            this.miner = miner;
            return this;
        }

        public BlockProxyBuilder withNonce(String nonce) {
            this.nonce = nonce;
            return this;
        }

        public BlockProxyBuilder withExtraData(String extraData) {
            this.extraData = extraData;
            return this;
        }

        public BlockProxyBuilder withLogsBloom(String logsBloom) {
            this.logsBloom = logsBloom;
            return this;
        }

        public BlockProxyBuilder withMixHash(String mixHash) {
            this.mixHash = mixHash;
            return this;
        }

        public BlockProxyBuilder withGasUsed(Wei gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public BlockProxyBuilder withGasLimit(Wei gasLimit) {
            this.gasLimit = gasLimit;
            return this;
        }

        public BlockProxyBuilder withSha3Uncles(String sha3Uncles) {
            this.sha3Uncles = sha3Uncles;
            return this;
        }

        public BlockProxyBuilder withUncles(List<String> uncles) {
            this.uncles = uncles;
            return this;
        }

        public BlockProxyBuilder withReceiptsRoot(String receiptsRoot) {
            this.receiptsRoot = receiptsRoot;
            return this;
        }

        public BlockProxyBuilder withTransactionsRoot(String transactionsRoot) {
            this.transactionsRoot = transactionsRoot;
            return this;
        }

        public BlockProxyBuilder withTransactions(List<TxProxy> transactions) {
            this.transactions = transactions;
            return this;
        }

        public BlockProxy build() {
            BlockProxy blockProxy = new BlockProxy();
            blockProxy.mixHash = this.mixHash;
            blockProxy.totalDifficulty = this.totalDifficulty;
            blockProxy.nonce = this.nonce;
            blockProxy.uncles = this.uncles;
            blockProxy.transactionsRoot = this.transactionsRoot;
            blockProxy.number = String.valueOf(this.number);
            blockProxy.logsBloom = this.logsBloom;
            blockProxy.receiptsRoot = this.receiptsRoot;
            blockProxy.hash = this.hash;
            blockProxy.parentHash = this.parentHash;
            blockProxy._size = this.size;
            blockProxy.difficulty = this.difficulty;
            if (this.gasLimit != null) {
                blockProxy._gasLimit = this.gasLimit;
            }
            if (this.gasUsed != null) {
                blockProxy._gasUsed = this.gasUsed;
            }
            blockProxy.size = String.valueOf(this.size);
            blockProxy.extraData = this.extraData;
            blockProxy.stateRoot = this.stateRoot;
            blockProxy.sha3Uncles = this.sha3Uncles;
            blockProxy.miner = this.miner;
            if (this.timestamp != null) {
                blockProxy.timestamp = String.valueOf(this.timestamp.toEpochSecond(ZoneOffset.UTC));
                blockProxy._timestamp = this.timestamp;
            }
            blockProxy.transactions = this.transactions;
            blockProxy._number = this.number;
            return blockProxy;
        }
    }
}
