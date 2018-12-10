package io.api.etherscan.statistic;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.model.Price;
import org.junit.Assert;
import org.junit.Test;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class StatisticPriceApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        Price price = api.stats().lastPrice();
        assertNotNull(price);
        assertNotNull(price.btcTimestamp());
        assertNotNull(price.usdTimestamp());
        assertNotEquals(0, price.inBtc());
        assertNotEquals(0, price.inUsd());
        assertNotNull(price.toString());
    }
}
