package io.api.etherscan.proxy;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.util.BasicUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ProxyCodeApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        Optional<String> call = api.proxy().code("0xf75e354c5edc8efed9b59ee9f67a80845ade7d0c");
        assertTrue(call.isPresent());
        assertFalse(BasicUtils.isNotHex(call.get()));
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        Optional<String> call = api.proxy().code("0f75e354c5edc8efed9b59ee9f67a80845ade7d0c");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<String> call = api.proxy().code("0xf15e354c5edc8efed9b59ee9f67a80845ade7d0c");
        assertTrue(call.isPresent());
        assertFalse(BasicUtils.isNotHex(call.get()));
    }
}
