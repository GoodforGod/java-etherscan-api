package io.goodforgod.api.etherscan.statistic;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.model.Wei;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class StatisticSupplyApiTests extends ApiRunner {

    @Test
    void correct() {
        Wei supply = getApi().stats().supply();
        assertNotNull(supply);
        assertNotNull(supply.asWei());
        assertNotNull(supply.asGwei());
        assertNotNull(supply.asKwei());
        assertNotNull(supply.asMwei());
        assertNotNull(supply.asEther());
        assertNotNull(supply.toString());

        Wei empty = Wei.ofWei(BigInteger.ONE);
        assertNotEquals(supply, empty);
        assertNotEquals(supply.hashCode(), empty.hashCode());
    }
}
