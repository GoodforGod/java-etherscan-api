package io.api.etherscan.statistic;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.model.Supply;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class StatisticSupplyApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        Supply supply = api.stats().supply();
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
