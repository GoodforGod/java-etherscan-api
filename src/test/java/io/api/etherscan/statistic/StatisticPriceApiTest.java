package io.api.etherscan.statistic;

import io.api.ApiRunner;
import io.api.etherscan.model.Price;
import org.junit.Test;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class StatisticPriceApiTest extends ApiRunner {

    @Test
    public void correct() {
        Price price = getApi().stats().lastPrice();
        assertNotNull(price);
        assertNotNull(price.btcTimestamp());
        assertNotNull(price.usdTimestamp());
        assertNotEquals(0.0, price.inBtc());
        assertNotEquals(0.0, price.inUsd());
        assertNotNull(price.toString());

        Price empty = new Price();
        assertNotEquals(price, empty);
        assertNotEquals(price.hashCode(), empty.hashCode());
    }
}
