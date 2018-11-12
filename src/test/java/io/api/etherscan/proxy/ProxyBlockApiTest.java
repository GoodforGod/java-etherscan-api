package io.api.etherscan.proxy;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.model.proxy.BlockProxy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ProxyBlockApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        Optional<BlockProxy> block = api.proxy().block(5120);
        assertTrue(block.isPresent());
        assertNotNull(block.get().getHash());
        assertNotNull(block.get().getNumber());
        assertNotNull(block.get().getParentHash());
        assertNotNull(block.get().getStateRoot());
        assertNotNull(block.get().getSize());
        assertNotNull(block.get().getDifficulty());
        assertNotNull(block.get().getTotalDifficulty());
        assertNotNull(block.get().getTimeStamp());
        assertNotNull(block.get().getMiner());
        assertNotNull(block.get().getNonce());
        assertNotNull(block.get().getHash());
        assertNotNull(block.get().getExtraData());
        assertNotNull(block.get().getLogsBloom());
        assertNotNull(block.get().getMixHash());
        assertNotNull(block.get().getGasUsed());
        assertNotNull(block.get().getGasLimit());
        assertNotNull(block.get().getSha3Uncles());
        assertNotNull(block.get().getTransactions());
        assertNotNull(block.get().getTransactionsRoot());
        assertNotNull(block.get().getReceiptsRoot());
        assertNotNull(block.get().getUncles());
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<BlockProxy> block = api.proxy().block(99999999999L);
        assertFalse(block.isPresent());
    }

    @Test
    public void correctParamNegativeNo() {
        Optional<BlockProxy> block = api.proxy().block(-1);
        assertTrue(block.isPresent());
        assertNotNull(block.get().getHash());
    }
}
