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
public class ProxyStorageApiTest extends Assert{

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        Optional<String> call = api.proxy().storageAt("0x6e03d9cce9d60f3e9f2597e13cd4c54c55330cfd", 0);
        assertTrue(call.isPresent());
        assertFalse(BasicUtils.isNotHex(call.get()));
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        Optional<String> call = api.proxy().storageAt("0xe03d9cce9d60f3e9f2597e13cd4c54c55330cfd", 0);
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<String> call = api.proxy().storageAt("0x6e03d9cce9d60f3e9f2597e13cd4c54c55330cfd", 100);
        assertTrue(call.isPresent());
        assertFalse(BasicUtils.isNotHex(call.get()));
    }
}
