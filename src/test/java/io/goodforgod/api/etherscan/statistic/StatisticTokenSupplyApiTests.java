package io.goodforgod.api.etherscan.statistic;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.error.EtherScanInvalidAddressException;
import io.goodforgod.api.etherscan.model.Wei;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class StatisticTokenSupplyApiTests extends ApiRunner {

    @Test
    void correct() {
        Wei supply = getApi().stats().supply("0x57d90b64a1a57749b0f932f1a3395792e12e7055");
        assertNotNull(supply);
        assertNotEquals(BigInteger.ZERO, supply);
    }

    @Test
    void invalidParamWithError() {
        assertThrows(EtherScanInvalidAddressException.class,
                () -> getApi().stats().supply("0x7d90b64a1a57749b0f932f1a3395792e12e7055"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Wei supply = getApi().stats().supply("0x51d90b64a1a57749b0f932f1a3395792e12e7055");
        assertNotNull(supply);
        assertEquals(0, supply.asEther().intValue());
    }
}
