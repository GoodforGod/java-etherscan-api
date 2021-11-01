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
public class ProxyStorageApiTest extends ApiRunner {

    @Test
    public void correct() {
        Optional<String> call = getApi().proxy().storageAt("0x6e03d9cce9d60f3e9f2597e13cd4c54c55330cfd", 0);
        assertTrue(call.isPresent());
        assertFalse(BasicUtils.isNotHex(call.get()));
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        getApi().proxy().storageAt("0xe03d9cce9d60f3e9f2597e13cd4c54c55330cfd", 0);
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        final Optional<String> call = getApi().proxy().storageAt("0x6e03d9cce9d60f3e9f2597e13cd4c54c55330cfd", 10000);
        assertFalse(call.isPresent());
    }
}
