package io.api.etherscan.block;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.model.UncleBlock;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class BlockApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        Optional<UncleBlock> uncle = api.block().uncles(2165403);
        assertTrue(uncle.isPresent());
        assertFalse(uncle.get().isEmpty());
        assertNotNull(uncle.get().getBlockMiner());
        assertNotNull(uncle.get().getUncleInclusionReward());
        assertNotNull(uncle.get().getUncles());
        assertFalse(uncle.get().getUncles().isEmpty());
        assertNotNull(uncle.get().getUncles().get(0).getBlockreward());
        assertNotNull(uncle.get().getUncles().get(0).getMiner());
        assertNotNull(uncle.get().toString());

        UncleBlock empty = new UncleBlock();
        assertNotEquals(uncle.get().hashCode(), empty.hashCode());
        assertNotEquals(uncle.get(), empty);
        assertTrue(empty.isEmpty());
    }

    @Test
    public void correctNoUncles() {
        Optional<UncleBlock> uncles = api.block().uncles(34);
        assertTrue(uncles.isPresent());
        assertTrue(uncles.get().getUncles().isEmpty());
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<UncleBlock> uncles = api.block().uncles(99999999934L);
        assertFalse(uncles.isPresent());
    }
}
