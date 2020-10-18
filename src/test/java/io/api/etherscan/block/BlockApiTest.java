package io.api.etherscan.block;

import io.api.ApiRunner;
import io.api.etherscan.model.UncleBlock;
import org.junit.Test;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class BlockApiTest extends ApiRunner {

    @Test
    public void correct() {
        Optional<UncleBlock> uncle = getApi().block().uncles(2165403);
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

        UncleBlock empty = new UncleBlock();
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
    public void correctNoUncles() {
        Optional<UncleBlock> uncles = getApi().block().uncles(34);
        assertTrue(uncles.isPresent());
        assertTrue(uncles.get().getUncles().isEmpty());
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<UncleBlock> uncles = getApi().block().uncles(99999999934L);
        assertFalse(uncles.isPresent());
    }
}
