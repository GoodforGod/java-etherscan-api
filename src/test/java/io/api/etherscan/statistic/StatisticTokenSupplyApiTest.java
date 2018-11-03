package io.api.etherscan.statistic;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class StatisticTokenSupplyApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        BigInteger supply = api.stats().supply("0x57d90b64a1a57749b0f932f1a3395792e12e7055");
        assertNotNull(supply);
        assertNotEquals(0, supply);
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        BigInteger supply = api.stats().supply("0x7d90b64a1a57749b0f932f1a3395792e12e7055");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        BigInteger supply = api.stats().supply("0x51d90b64a1a57749b0f932f1a3395792e12e7055");
        assertNotNull(supply);
        assertEquals(0, supply.intValue());
    }
}
