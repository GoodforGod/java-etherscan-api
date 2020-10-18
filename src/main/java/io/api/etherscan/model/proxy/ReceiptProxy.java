package io.api.etherscan.model.proxy;

import io.api.etherscan.model.Log;
import io.api.etherscan.util.BasicUtils;

import java.math.BigInteger;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ReceiptProxy {

    private String root;
    private String from;
    private String to;
    private String blockNumber;
    private Long _blockNumber;
    private String blockHash;
    private String transactionHash;
    private String transactionIndex;
    private Long _transactionIndex;
    private String gasUsed;
    private BigInteger _gasUsed;
    private String cumulativeGasUsed;
    private BigInteger _cumulativeGasUsed;
    private String contractAddress;

    private List<Log> logs;
    private String logsBloom;

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

        if (blockNumber != null ? !blockNumber.equals(that.blockNumber) : that.blockNumber != null)
            return false;
        if (transactionHash != null ? !transactionHash.equals(that.transactionHash) : that.transactionHash != null)
            return false;
        return transactionIndex != null ? transactionIndex.equals(that.transactionIndex) : that.transactionIndex == null;
    }

    @Override
    public int hashCode() {
        int result = blockNumber != null ? blockNumber.hashCode() : 0;
        result = 31 * result + (transactionHash != null ? transactionHash.hashCode() : 0);
        result = 31 * result + (transactionIndex != null ? transactionIndex.hashCode() : 0);
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
}
