package io.api.etherscan.proxy;

import io.api.ApiRunner;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.util.BasicUtils;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class ProxyCodeApiTest extends ApiRunner {

    @Test
    void correct() {
        Optional<String> call = getApi().proxy().code("0xf75e354c5edc8efed9b59ee9f67a80845ade7d0c");
        assertTrue(call.isPresent());
        assertFalse(BasicUtils.isNotHex(call.get()), call.get());
    }

    @Test
    void invalidParamWithError() {
        assertThrows(InvalidAddressException.class, () -> getApi().proxy().code("0f75e354c5edc8efed9b59ee9f67a80845ade7d0c"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Optional<String> call = getApi().proxy().code("0xf15e354c5edc8efed9b59ee9f67a80845ade7d0c");
        assertTrue(call.isPresent());
        assertFalse(BasicUtils.isNotHex(call.get()), call.get());
    }
}
