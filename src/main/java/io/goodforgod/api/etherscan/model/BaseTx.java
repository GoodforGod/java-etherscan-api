package io.goodforgod.api.etherscan.model;

import com.google.gson.annotations.Expose;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 28.10.2018
 */
abstract class BaseTx {

    long blockNumber;
    String timeStamp;
    @Expose(deserialize = false, serialize = false)
    LocalDateTime _timeStamp;
    String hash;
    String from;
    String to;
    String contractAddress;
    String input;
    BigInteger gas;
    BigInteger gasUsed;

    // <editor-fold desc="Getter">
    public long getBlockNumber() {
        return blockNumber;
    }

    public LocalDateTime getTimeStamp() {
        if (_timeStamp == null && !BasicUtils.isEmpty(timeStamp))
            _timeStamp = LocalDateTime.ofEpochSecond(Long.parseLong(timeStamp), 0, ZoneOffset.UTC);
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
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BaseTx))
            return false;
        BaseTx baseTx = (BaseTx) o;
        return blockNumber == baseTx.blockNumber && Objects.equals(timeStamp, baseTx.timeStamp)
                && Objects.equals(hash, baseTx.hash) && Objects.equals(from, baseTx.from) && Objects.equals(to, baseTx.to)
                && Objects.equals(contractAddress, baseTx.contractAddress) && Objects.equals(input, baseTx.input)
                && Objects.equals(gas, baseTx.gas) && Objects.equals(gasUsed, baseTx.gasUsed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockNumber, timeStamp, hash, from, to, contractAddress, input, gas, gasUsed);
    }

    @Override
    public String toString() {
        return "BaseTx{" +
                "blockNumber=" + blockNumber +
                ", timeStamp='" + timeStamp + '\'' +
                ", hash='" + hash + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", contractAddress='" + contractAddress + '\'' +
                ", input='" + input + '\'' +
                ", gas=" + gas +
                ", gasUsed=" + gasUsed +
                '}';
    }
}
