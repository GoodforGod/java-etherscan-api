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
        Optional<UncleBlock> uncles = api.block().uncles(2165403);
        assertTrue(uncles.isPresent());
        assertFalse(uncles.get().isEmpty());
        assertNotNull(uncles.get().getBlockMiner());
        assertNotNull(uncles.get().getUncleInclusionReward());
        assertNotNull(uncles.get().getUncles());
        assertFalse(uncles.get().getUncles().isEmpty());
        assertNotNull(uncles.get().getUncles().get(0).getBlockreward());
        assertNotNull(uncles.get().getUncles().get(0).getMiner());
        assertNotNull(uncles.get().toString());

        UncleBlock empty = new UncleBlock();
        assertNotEquals(empty.hashCode(), uncles.get().hashCode());
        assertNotEquals(uncles, empty);
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
