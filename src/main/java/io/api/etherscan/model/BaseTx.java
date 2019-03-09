package io.api.etherscan.model;

import io.api.etherscan.util.BasicUtils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
abstract class BaseTx {

    private long blockNumber;
    private String timeStamp;
    private LocalDateTime _timeStamp;
    private String hash;
    private String from;
    private String to;
    private BigInteger value;
    private String contractAddress;
    private String input;
    private BigInteger gas;
    private BigInteger gasUsed;

    //<editor-fold desc="Getter">
    public long getBlockNumber() {
        return blockNumber;
    }

    public LocalDateTime getTimeStamp() {
        if(_timeStamp == null && !BasicUtils.isEmpty(timeStamp))
            _timeStamp = LocalDateTime.ofEpochSecond(Long.valueOf(timeStamp), 0, ZoneOffset.UTC);
        return _timeStamp;
    }

    public String getHash() {
        return hash;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigInteger getValue() {
        return value;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public String getInput() {
        return input;
    }

    public BigInteger getGas() {
        return gas;
    }

    public BigInteger getGasUsed() {
        return gasUsed;
    }
    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseTx baseTx = (BaseTx) o;

        if (blockNumber != baseTx.blockNumber) return false;
        if (timeStamp != null ? !timeStamp.equals(baseTx.timeStamp) : baseTx.timeStamp != null) return false;
        if (_timeStamp != null ? !_timeStamp.equals(baseTx._timeStamp) : baseTx._timeStamp != null) return false;
        if (hash != null ? !hash.equals(baseTx.hash) : baseTx.hash != null) return false;
        if (from != null ? !from.equals(baseTx.from) : baseTx.from != null) return false;
        if (to != null ? !to.equals(baseTx.to) : baseTx.to != null) return false;
        if (value != null ? !value.equals(baseTx.value) : baseTx.value != null) return false;
        if (contractAddress != null ? !contractAddress.equals(baseTx.contractAddress) : baseTx.contractAddress != null)
            return false;
        if (input != null ? !input.equals(baseTx.input) : baseTx.input != null) return false;
        if (gas != null ? !gas.equals(baseTx.gas) : baseTx.gas != null) return false;
        return gasUsed != null ? gasUsed.equals(baseTx.gasUsed) : baseTx.gasUsed == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (blockNumber ^ (blockNumber >>> 32));
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (_timeStamp != null ? _timeStamp.hashCode() : 0);
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (contractAddress != null ? contractAddress.hashCode() : 0);
        result = 31 * result + (input != null ? input.hashCode() : 0);
        result = 31 * result + (gas != null ? gas.hashCode() : 0);
        result = 31 * result + (gasUsed != null ? gasUsed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BaseTx{" +
                "blockNumber=" + blockNumber +
                ", timeStamp='" + timeStamp + '\'' +
                ", _timeStamp=" + _timeStamp +
                ", hash='" + hash + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", value=" + value +
                ", contractAddress='" + contractAddress + '\'' +
                ", input='" + input + '\'' +
                ", gas=" + gas +
                ", gasUsed=" + gasUsed +
                '}';
    }
}
