package io.goodforgod.api.etherscan.statistic;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.model.Price;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class StatisticPriceApiTest extends ApiRunner {

    @Test
    void correct() {
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
