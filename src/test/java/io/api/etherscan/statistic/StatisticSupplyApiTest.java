package io.api.etherscan.statistic;

import io.api.ApiRunner;
import io.api.etherscan.model.Supply;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class StatisticSupplyApiTest extends ApiRunner {

    @Test
    void correct() {
        Supply supply = getApi().stats().supply();
        assertNotNull(supply);
        assertNotNull(supply.getValue());
        assertNotNull(supply.asGwei());
        assertNotNull(supply.asKwei());
        assertNotNull(supply.asMwei());
        assertNotNull(supply.asEther());
        assertNotNull(supply.toString());

        Supply empty = new Supply(BigInteger.ONE);
        assertNotEquals(supply, empty);
        assertNotEquals(supply.hashCode(), empty.hashCode());
    }
}
