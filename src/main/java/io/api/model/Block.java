package io.api.model;

import io.api.util.BasicUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class Block {

    private String blockNumber;
    private String timeStamp;
    private LocalDateTime _timeStamp;
    private String blockReward;

    public Block() {}

    public Block(String blockNumber, String timeStamp, String blockReward) {
        this.blockNumber = blockNumber;
        this.timeStamp = timeStamp;
        this.blockReward = blockReward;
    }

    //<editor-fold desc="Getter">
    public String getBlockNumber() {
        return blockNumber;
    }

    public LocalDateTime getTimeStamp() {
        if(_timeStamp == null && !BasicUtils.isEmpty(timeStamp))
            _timeStamp = LocalDateTime.ofEpochSecond(Long.valueOf(timeStamp), 0, ZoneOffset.UTC);
        return _timeStamp;
    }

    public String getBlockReward() {
        return blockReward;
    }
    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        return blockNumber != null ? blockNumber.equals(block.blockNumber) : block.blockNumber == null;
    }

    @Override
    public int hashCode() {
        return blockNumber != null ? blockNumber.hashCode() : 0;
    }
}
