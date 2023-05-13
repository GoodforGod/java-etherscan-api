package io.goodforgod.api.etherscan.gastracker;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.model.GasEstimate;
import io.goodforgod.api.etherscan.model.GasOracle;
import io.goodforgod.api.etherscan.model.Wei;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 14.05.2023
 */
class GasTrackerApiTest extends ApiRunner {

    @Test
    void estimate() {
        GasEstimate estimate = getApi().gasTracker().estimate(new Wei(123));
        assertNotNull(estimate);
        assertNotNull(estimate.getDuration());
    }

    @Test
    void oracle() {
        GasOracle oracle = getApi().gasTracker().oracle();
        assertNotNull(oracle);
        assertNotNull(oracle.getGasUsedRatio());
        assertNotNull(oracle.getFastGasPriceInWei());
        assertNotNull(oracle.getLastBlock());
        assertNotNull(oracle.getProposeGasPriceInWei());
        assertNotNull(oracle.getSafeGasPriceInWei());
        assertNotNull(oracle.getSuggestBaseFee());
    }
}
