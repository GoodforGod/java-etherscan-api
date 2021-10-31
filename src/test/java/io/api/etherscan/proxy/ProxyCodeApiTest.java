package io.api.etherscan.proxy;

import io.api.ApiRunner;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.util.BasicUtils;
import org.junit.Test;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ProxyCodeApiTest extends ApiRunner {

    @Test
    public void correct() {
        Optional<String> call = getApi().proxy().code("0xf75e354c5edc8efed9b59ee9f67a80845ade7d0c");
        assertTrue(call.isPresent());
        assertFalse(call.get(), BasicUtils.isNotHex(call.get()));
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        getApi().proxy().code("0f75e354c5edc8efed9b59ee9f67a80845ade7d0c");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<String> call = getApi().proxy().code("0xf15e354c5edc8efed9b59ee9f67a80845ade7d0c");
        assertTrue(call.isPresent());
        assertFalse(call.get(), BasicUtils.isNotHex(call.get()));
    }
}
