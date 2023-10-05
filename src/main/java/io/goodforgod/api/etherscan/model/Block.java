package io.goodforgod.api.etherscan.model;

import com.google.gson.annotations.Expose;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * @author GoodforGod
 * @since 28.10.2018
 */
public class Block implements Comparable<Block> {

    long blockNumber;
    BigInteger blockReward;
    String timeStamp;
    @Expose(deserialize = false, serialize = false)
    LocalDateTime _timeStamp;

    protected Block() {}

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
        if (!(o instanceof Block))
            return false;
        Block block = (Block) o;
        return blockNumber == block.blockNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockNumber);
    }

    @Override
    public String toString() {
        return "Block{" +
                "blockNumber=" + blockNumber +
                ", blockReward=" + blockReward +
                ", timeStamp=" + timeStamp +
                '}';
    }

    @Override
    public int compareTo(@NotNull Block o) {
        return Long.compare(blockNumber, o.blockNumber);
    }

    public static BlockBuilder builder() {
        return new BlockBuilder();
    }

    public static class BlockBuilder {

        private long blockNumber;
        private BigInteger blockReward;
        private LocalDateTime timeStamp;

        BlockBuilder() {}

        public BlockBuilder withBlockNumber(long blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public BlockBuilder withBlockReward(BigInteger blockReward) {
            this.blockReward = blockReward;
            return this;
        }

        public BlockBuilder withTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Block build() {
            Block block = new Block();
            block.blockNumber = this.blockNumber;
            block.blockReward = this.blockReward;
            if (this.timeStamp != null) {
                block._timeStamp = this.timeStamp;
                block.timeStamp = String.valueOf(this.timeStamp.toEpochSecond(ZoneOffset.UTC));
            }
            return block;
        }
    }
}
