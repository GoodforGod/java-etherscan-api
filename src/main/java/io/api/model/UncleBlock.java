package io.api.model;

import io.api.util.BasicUtils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class UncleBlock {

    private long blockNumber;
    private BigInteger blockReward;
    private String blockMiner;
    private String timeStamp;
    private LocalDateTime _timeStamp;
    private List<Uncle> uncles;
    private String uncleInclusionReward;

    public UncleBlock() { }

    public UncleBlock(long blockNumber, BigInteger blockReward, String blockMiner,
                      LocalDateTime _timeStamp, List<Uncle> uncles, String uncleInclusionReward) {
        this.blockNumber = blockNumber;
        this.blockReward = blockReward;
        this.blockMiner = blockMiner;
        this._timeStamp = _timeStamp;
        this.uncles = uncles;
        this.uncleInclusionReward = uncleInclusionReward;
    }

    //<editor-fold desc="Getters">

    public boolean isEmpty() {
        return blockNumber == 0 && blockReward == null
                && BasicUtils.isEmpty(timeStamp)
                && BasicUtils.isEmpty(blockMiner);
    }

    public LocalDateTime getTimeStamp() {
        if(_timeStamp == null && !BasicUtils.isEmpty(timeStamp))
            _timeStamp = LocalDateTime.ofEpochSecond(Long.valueOf(timeStamp), 0, ZoneOffset.UTC);
        return _timeStamp;
    }

    public BigInteger getBlockReward() {
        return blockReward;
    }

    public String getBlockMiner() {
        return blockMiner;
    }

    public List<Uncle> getUncles() {
        return uncles;
    }

    public String getUncleInclusionReward() {
        return uncleInclusionReward;
    }

    public long getBlockNumber() {
        return blockNumber;
    }
    //</editor-fold>
}
