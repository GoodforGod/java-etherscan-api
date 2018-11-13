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

    //<editor-fold desc="Getters">
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
        if(_blockNumber == null && !BasicUtils.isEmpty(blockNumber))
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
        if(_transactionIndex == null && !BasicUtils.isEmpty(transactionIndex))
            _transactionIndex = BasicUtils.parseHex(transactionIndex).longValue();
        return _transactionIndex;
    }

    public BigInteger getGasUsed() {
        if(_gasUsed == null && !BasicUtils.isEmpty(gasUsed))
            _gasUsed = BasicUtils.parseHex(gasUsed);
        return _gasUsed;
    }

    public BigInteger getCumulativeGasUsed() {
        if(_cumulativeGasUsed == null && !BasicUtils.isEmpty(cumulativeGasUsed))
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
    //</editor-fold>
}
