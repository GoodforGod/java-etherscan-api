package io.api.etherscan.account;

import io.api.ApiRunner;
import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Block;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
@RunWith(Parameterized.class)
public class AccountMinedBlocksTest extends ApiRunner {

    private final EtherScanApi api;
    private final int blocksMined;
    private final String addressCorrect;
    private final String addressInvalid;
    private final String addressNoResponse;

    public AccountMinedBlocksTest(EtherScanApi api,
                                  int blocksMined,
                                  String addressCorrect,
                                  String addressInvalid,
                                  String addressNoResponse) {
        this.api = api;
        this.blocksMined = blocksMined;
        this.addressCorrect = addressCorrect;
        this.addressInvalid = addressInvalid;
        this.addressNoResponse = addressNoResponse;
    }

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {
                        getApi(),
                        223,
                        "0xE4C6175183029A0f039bf2DFffa5C6e8F3cA9B23",
                        "xE4C6175183029A0f039bf2DFffa5C6e8F3cA9B23",
                        "0xE1C6175183029A0f039bf2DFffa5C6e8F3cA9B23",
                }
        });
    }

    @Test
    public void correct() {
        List<Block> blocks = api.account().minedBlocks(addressCorrect);
        assertNotNull(blocks);

        assertEquals(blocksMined, blocks.size());
        assertBlocks(blocks);
        assertNotNull(blocks.get(0).toString());

        if (blocks.size() > 1) {
            assertNotEquals(blocks.get(0), blocks.get(1));
            assertNotEquals(blocks.get(0).hashCode(), blocks.get(1).hashCode());
        }
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        List<Block> txs = getApi().account().minedBlocks(addressInvalid);
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        List<Block> txs = api.account().minedBlocks(addressNoResponse);
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
