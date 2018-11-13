package io.api.etherscan.model.proxy;

import io.api.etherscan.util.BasicUtils;

import java.math.BigInteger;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class TxProxy {

    private String to;
    private String hash;
    private String transactionIndex;
    private Long _transactionIndex;
    private String from;
    private String v;
    private String input;
    private String s;
    private String r;
    private String nonce;
    private Long _nonce;
    private String value;
    private String gas;
    private BigInteger _gas;
    private String gasPrice;
    private BigInteger _gasPrice;
    private String blockHash;
    private String blockNumber;
    private Long _blockNumber;

    //<editor-fold desc="Getters">
    public String getTo() {
        return to;
    }

    public String getHash() {
        return hash;
    }

    public Long getTransactionIndex() {
        if(_transactionIndex == null && !BasicUtils.isEmpty(transactionIndex))
            _transactionIndex = BasicUtils.parseHex(transactionIndex).longValue();
        return _transactionIndex;
    }

    public String getFrom() {
        return from;
    }

    public BigInteger getGas() {
        if(_gas == null && !BasicUtils.isEmpty(gas))
            _gas = BasicUtils.parseHex(gas);
        return _gas;
    }

    public String getV() {
        return v;
    }

    public String getInput() {
        return input;
    }

    public String getS() {
        return s;
    }

    public String getR() {
        return r;
    }

    public Long getNonce() {
        if(_nonce == null && !BasicUtils.isEmpty(nonce))
            _nonce = BasicUtils.parseHex(nonce).longValue();
        return _nonce;
    }

    public String getValue() {
        return value;
    }

    public BigInteger getGasPrice() {
        if(_gasPrice == null && !BasicUtils.isEmpty(gasPrice))
            _gasPrice = BasicUtils.parseHex(gasPrice);
        return _gasPrice;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public Long getBlockNumber() {
        if(_blockNumber == null && !BasicUtils.isEmpty(blockNumber))
            _blockNumber = BasicUtils.parseHex(blockNumber).longValue();
        return _blockNumber;
    }
    //</editor-fold>
}
