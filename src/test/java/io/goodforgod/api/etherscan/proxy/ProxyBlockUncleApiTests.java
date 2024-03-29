package io.goodforgod.api.etherscan.proxy;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.model.proxy.BlockProxy;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 13.11.2018
 */
class ProxyBlockUncleApiTests extends ApiRunner {

    @Test
    void correct() {
        Optional<BlockProxy> block = getApi().proxy().blockUncle(603183, 0);
        assertTrue(block.isPresent());
        assertNotNull(block.get().getHash());
        assertNotNull(block.get().toString());
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Optional<BlockProxy> block = getApi().proxy().blockUncle(5120, 1);
        assertFalse(block.isPresent());
    }

    @Test
    void correctParamNegativeNo() {
        Optional<BlockProxy> block = getApi().proxy().blockUncle(-603183, 0);
        assertFalse(block.isPresent());
    }
}
