package io.api.etherscan.statistic;

import io.api.ApiRunner;
import io.api.etherscan.model.Supply;
import org.junit.Test;

import java.math.BigInteger;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class StatisticSupplyApiTest extends ApiRunner {

    @Test
    public void correct() {
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
