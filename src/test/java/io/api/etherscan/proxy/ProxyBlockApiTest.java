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
