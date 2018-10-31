package io.api.model.utility;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class BlockParam {
    private long startBlock;
    private long endBlock;

    public BlockParam(long startBlock, long endBlock) {
        this.startBlock = startBlock;
        this.endBlock = endBlock;
    }

    public long getStartBlock() {
        return startBlock;
    }

    public long getEndBlock() {
        return endBlock;
    }
}
