package io.api.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class Block {

    private String blockNumber;
    private String timeStamp;
    private String blockReward;

    public Block(String blockNumber, String timeStamp, String blockReward) {
        this.blockNumber = blockNumber;
        this.timeStamp = timeStamp;
        this.blockReward = blockReward;
    }

    //<editor-fold desc="Getter">
    public String getBlockNumber() {
        return blockNumber;
    }

    public String getTimeStamp() {
        return timeStamp;
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
