package io.api.etherscan.proxy;

import io.api.ApiRunner;
import io.api.etherscan.error.InvalidAddressException;
import org.junit.Test;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ProxyTxCountApiTest extends ApiRunner {

    @Test
    public void correctSended() {
        int count = getApi().proxy().txSendCount("0x2910543af39aba0cd09dbb2d50200b3e800a63d2");
        assertNotEquals(0, count);
    }

    @Test
    public void correctByBlockNo() {
        int count = getApi().proxy().txCount(6137420);
        assertNotEquals(0, count);
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        int count = getApi().proxy().txSendCount("0xe03d9cce9d60f3e9f2597e13cd4c54c55330cfd");
    }

    @Test
    public void correctParamWithEmptyExpectedResultBlockNoExist() {
        int count = getApi().proxy().txCount(99999999999L);
        assertNotEquals(1, count);
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        int count = getApi().proxy().txSendCount("0x1e03d9cce9d60f3e9f2597e13cd4c54c55330cfd");
        assertNotEquals(1, count);
    }
}
