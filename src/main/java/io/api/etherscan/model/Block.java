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
public class Block {

    private long blockNumber;
    private BigInteger blockReward;
    private String timeStamp;
    private LocalDateTime _timeStamp;

    // <editor-fold desc="Getter">
    public long getBlockNumber() {
        return blockNumber;
    }

    public LocalDateTime getTimeStamp() {
        if (_timeStamp == null && !BasicUtils.isEmpty(timeStamp))
            _timeStamp = LocalDateTime.ofEpochSecond(Long.parseLong(timeStamp), 0, ZoneOffset.UTC);
        return _timeStamp;
    }

    public BigInteger getBlockReward() {
        return blockReward;
    }
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Block block = (Block) o;

        return blockNumber == block.blockNumber;
    }

    @Override
    public int hashCode() {
        return (int) (blockNumber ^ (blockNumber >>> 32));
    }

    @Override
    public String toString() {
        return "Block{" +
                "blockNumber=" + blockNumber +
                ", blockReward=" + blockReward +
                ", timeStamp='" + timeStamp + '\'' +
                ", _timeStamp=" + _timeStamp +
                '}';
    }
}
