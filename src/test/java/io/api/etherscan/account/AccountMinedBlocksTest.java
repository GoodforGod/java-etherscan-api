package io.api.etherscan.account;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Block;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class AccountMinedBlocksTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        List<Block> blocks = api.account().minedBlocks("0xE4C6175183029A0f039bf2DFffa5C6e8F3cA9B23");
        assertNotNull(blocks);
        assertEquals(223, blocks.size());
        assertTxs(blocks);
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        List<Block> txs = api.account().minedBlocks("0xEC6175183029A0f039bf2DFffa5C6e8F3cA9B23");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        List<Block> txs = api.account().minedBlocks("0xE1C6175183029A0f039bf2DFffa5C6e8F3cA9B23");
        assertNotNull(txs);
        assertTrue(txs.isEmpty());
    }

    private void assertTxs(List<Block> blocks) {
        for (Block block : blocks) {
            assertNotEquals(0, block.getBlockNumber());
            assertNotNull(block.getBlockReward());
            assertNotNull(block.getTimeStamp());
        }
    }
}
