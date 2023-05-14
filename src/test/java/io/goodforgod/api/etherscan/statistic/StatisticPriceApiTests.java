package io.goodforgod.api.etherscan.statistic;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.model.Price;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class StatisticPriceApiTests extends ApiRunner {

    @Test
    void correct() {
        Price price = getApi().stats().priceLast();
        assertNotNull(price);
        assertNotNull(price.btcTimestamp());
        assertNotNull(price.usdTimestamp());
        assertNotEquals(0.0, price.inBtc());
        assertNotEquals(0.0, price.inUsd());
        assertNotNull(price.toString());

        Price empty = Price.builder().build();
        assertNotEquals(price, empty);
        assertNotEquals(price.hashCode(), empty.hashCode());
    }
}
