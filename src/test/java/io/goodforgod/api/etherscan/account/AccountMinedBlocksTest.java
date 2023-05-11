package io.goodforgod.api.etherscan.account;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.EtherScanAPI;
import io.goodforgod.api.etherscan.error.EtherScanInvalidAddressException;
import io.goodforgod.api.etherscan.model.Block;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class AccountMinedBlocksTest extends ApiRunner {

    private final EtherScanAPI api = getApi();

    @Test
    void correct() {
        List<Block> blocks = api.account().blocksMined("0xE4C6175183029A0f039bf2DFffa5C6e8F3cA9B23");
        assertNotNull(blocks);

        assertEquals(223, blocks.size());
        assertBlocks(blocks);
        assertNotNull(blocks.get(0).toString());

        if (blocks.size() > 1) {
            assertNotEquals(blocks.get(0), blocks.get(1));
            assertNotEquals(blocks.get(0).hashCode(), blocks.get(1).hashCode());
        }
    }

    @Test
    void invalidParamWithError() {
        assertThrows(EtherScanInvalidAddressException.class,
                () -> getApi().account().blocksMined("xE4C6175183029A0f039bf2DFffa5C6e8F3cA9B23"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        List<Block> txs = api.account().blocksMined("0xE1C6175183029A0f039bf2DFffa5C6e8F3cA9B23");
        assertNotNull(txs);
        assertTrue(txs.isEmpty());
    }

    private void assertBlocks(List<Block> blocks) {
        for (Block block : blocks) {
            assertNotEquals(0, block.getBlockNumber());
            assertNotNull(block.getBlockReward());
            assertNotNull(block.getTimeStamp());
        }
    }
}
