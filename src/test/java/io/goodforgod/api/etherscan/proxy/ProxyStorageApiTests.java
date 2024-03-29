package io.goodforgod.api.etherscan.proxy;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.error.EtherScanInvalidAddressException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class ProxyStorageApiTests extends ApiRunner {

    @Test
    void correct() {
        Optional<String> call = getApi().proxy().storageAt("0x6e03d9cce9d60f3e9f2597e13cd4c54c55330cfd", 0);
        assertFalse(call.isPresent());
    }

    @Test
    void invalidParamWithError() {
        assertThrows(EtherScanInvalidAddressException.class,
                () -> getApi().proxy().storageAt("0xe03d9cce9d60f3e9f2597e13cd4c54c55330cfd", 0));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        final Optional<String> call = getApi().proxy().storageAt("0x6e03d9cce9d60f3e9f2597e13cd4c54c55330cfd", 10000);
        assertFalse(call.isPresent());
    }
}
