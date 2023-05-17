package io.goodforgod.api.etherscan.statistic;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.model.EthSupply;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 14.05.2023
 */
class StatisticSupplyTotalApiTests extends ApiRunner {

    @Test
    void correct() {
        EthSupply supply = getApi().stats().supplyTotal();
        assertNotNull(supply);
        assertNotNull(supply.getBurntFees());
        assertNotEquals(0, supply.getBurntFees().asWei().intValue());
        assertNotNull(supply.getEthSupply());
        assertNotEquals(0, supply.getEthSupply().asWei().intValue());
        assertNotNull(supply.getEth2Staking());
        assertNotEquals(0, supply.getEth2Staking().asWei().intValue());
        assertNotNull(supply.getWithdrawnTotal());
        assertNotEquals(0, supply.getWithdrawnTotal().asWei().intValue());
        assertNotNull(supply.getTotal());
        assertNotEquals(0, supply.getTotal().asWei().intValue());
    }
}
