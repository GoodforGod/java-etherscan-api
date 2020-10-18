package io.api.etherscan.model.proxy;

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
public class BlockProxy {

    private String number;
    private Long _number;
    private String hash;
    private String parentHash;
    private String stateRoot;
    private String size;
    private Long _size;
    private String difficulty;
    private String totalDifficulty;
    private String timestamp;
    private LocalDateTime _timestamp;

    private String miner;
    private String nonce;
    private String extraData;
    private String logsBloom;
    private String mixHash;
    private String gasUsed;
    private BigInteger _gasUsed;
    private String gasLimit;
    private BigInteger _gasLimit;

    private String sha3Uncles;
    private List<String> uncles;

    private String receiptsRoot;
    private String transactionsRoot;
    private List<TxProxy> transactions;

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

    public BigInteger getGasUsed() {
        if (_gasUsed == null && !BasicUtils.isEmpty(gasUsed))
            _gasUsed = BasicUtils.parseHex(gasUsed);
        return _gasUsed;
    }

    public BigInteger getGasLimit() {
        if (_gasLimit == null && !BasicUtils.isEmpty(gasLimit))
            _gasLimit = BasicUtils.parseHex(gasLimit);
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

        if (number != null ? !number.equals(that.number) : that.number != null)
            return false;
        if (hash != null ? !hash.equals(that.hash) : that.hash != null)
            return false;
        return parentHash != null ? parentHash.equals(that.parentHash) : that.parentHash == null;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (parentHash != null ? parentHash.hashCode() : 0);
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
}
