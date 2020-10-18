package io.api.etherscan.proxy;

import io.api.ApiRunner;
import io.api.etherscan.model.proxy.BlockProxy;
import org.junit.Test;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.11.2018
 */
public class ProxyBlockUncleApiTest extends ApiRunner {

    @Test
    public void correct() {
        Optional<BlockProxy> block = getApi().proxy().blockUncle(603183, 0);
        assertTrue(block.isPresent());
        assertNotNull(block.get().getHash());
        assertNotNull(block.get().toString());
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<BlockProxy> block = getApi().proxy().blockUncle(5120, 1);
        assertFalse(block.isPresent());
    }

    @Test
    public void correctParamNegativeNo() {
        Optional<BlockProxy> block = getApi().proxy().blockUncle(-603183, 0);
        assertFalse(block.isPresent());
    }
}
