package io.api.etherscan.account;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Block;
import io.api.etherscan.model.EthNetwork;
import org.junit.Assert;
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
public class AccountMinedBlocksTest extends Assert {

    private EtherScanApi api;
    private int blocksMined;
    private String addressCorrect;
    private String addressInvalid;
    private String addressNoResponse;

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
        return Arrays.asList(new Object[][]{
                {
                        new EtherScanApi(),
                        223,
                        "0xE4C6175183029A0f039bf2DFffa5C6e8F3cA9B23",
                        "xE4C6175183029A0f039bf2DFffa5C6e8F3cA9B23",
                        "0xE1C6175183029A0f039bf2DFffa5C6e8F3cA9B23",
                },
                {
                        new EtherScanApi(EthNetwork.ROPSTEN),
                        1,
                        "0x0923DafEB5A5d11a83E188d5dbCdEd14f9b161a7",
                        "00923DafEB5A5d11a83E188d5dbCdEd14f9b161a7",
                        "0x1923DafEB5A5d11a83E188d5dbCdEd14f9b161a7",
                }
                // Other netWorks not presented due to 30k+ mined blocks, tests runs forever
        });
    }

    @Test
    public void correct() {
        List<Block> blocks = api.account().minedBlocks(addressCorrect);
        assertNotNull(blocks);

        assertEquals(blocksMined, blocks.size());
        assertBlocks(blocks);
        Block block = new Block();
        assertFalse(blocks.get(0).equals(block));
        assertNotNull(block.toString());
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        List<Block> txs = api.account().minedBlocks(addressInvalid);
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
