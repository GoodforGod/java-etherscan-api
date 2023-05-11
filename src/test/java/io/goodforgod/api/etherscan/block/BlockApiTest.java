package io.goodforgod.api.etherscan.block;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.model.BlockUncle;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class BlockApiTest extends ApiRunner {

    @Test
    void correct() {
        Optional<BlockUncle> uncle = getApi().block().uncles(2165403);
        assertTrue(uncle.isPresent());
        assertFalse(uncle.get().isEmpty());
        assertNotNull(uncle.get().getBlockMiner());
        assertNotNull(uncle.get().getUncleInclusionReward());
        assertNotNull(uncle.get().getUncles());
        assertFalse(uncle.get().getUncles().isEmpty());
        assertNotNull(uncle.get().getUncles().get(0).getBlockreward());
        assertNotNull(uncle.get().getUncles().get(0).getMiner());
        assertNotEquals(-1, uncle.get().getUncles().get(0).getUnclePosition());
        assertNotNull(uncle.get().toString());

        BlockUncle empty = new BlockUncle();
        assertNotEquals(uncle.get().hashCode(), empty.hashCode());
        assertNotEquals(uncle.get(), empty);
        assertTrue(empty.isEmpty());

        if (uncle.get().getUncles().size() > 0) {
            assertNotEquals(-1, uncle.get().getUncles().get(0).getUnclePosition());
            assertEquals(uncle.get().getUncles().get(0), uncle.get().getUncles().get(0));
            assertEquals(uncle.get().getUncles().get(0).hashCode(), uncle.get().getUncles().get(0).hashCode());
        }

        if (uncle.get().getUncles().size() > 1) {
            assertNotEquals(uncle.get().getUncles().get(1), uncle.get().getUncles().get(0));
            assertNotEquals(uncle.get().getUncles().get(1).hashCode(), uncle.get().getUncles().get(0).hashCode());
        }
    }

    @Test
    void correctNoUncles() {
        Optional<BlockUncle> uncles = getApi().block().uncles(34);
        assertTrue(uncles.isPresent());
        assertTrue(uncles.get().getUncles().isEmpty());
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Optional<BlockUncle> uncles = getApi().block().uncles(99999999934L);
        assertFalse(uncles.isPresent());
    }
}
