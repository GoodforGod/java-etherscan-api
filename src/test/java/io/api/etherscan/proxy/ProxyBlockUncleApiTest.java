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
 * @since 13.11.2018
 */
public class ProxyBlockUncleApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        Optional<BlockProxy> block = api.proxy().blockUncle(603183, 0);
        assertTrue(block.isPresent());
        assertNotNull(block.get().getHash());
        assertNotNull(block.get().toString());
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<BlockProxy> block = api.proxy().blockUncle(5120, 1);
        assertFalse(block.isPresent());
    }

    @Test
    public void correctParamNegativeNo() {
        Optional<BlockProxy> block = api.proxy().blockUncle(-603183, 0);
        assertFalse(block.isPresent());
    }
}
