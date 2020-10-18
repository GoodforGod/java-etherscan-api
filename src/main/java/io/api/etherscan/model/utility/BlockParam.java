package io.api.etherscan.model.utility;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class BlockParam {

    private final long startBlock;
    private final long endBlock;

    public BlockParam(long startBlock, long endBlock) {
        this.startBlock = startBlock;
        this.endBlock = endBlock;
    }

    public long start() {
        return startBlock;
    }

    public long end() {
        return endBlock;
    }
}
