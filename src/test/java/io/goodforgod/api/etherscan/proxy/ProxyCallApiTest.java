package io.goodforgod.api.etherscan.proxy;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.error.EtherScanInvalidAddressException;
import io.goodforgod.api.etherscan.error.EtherScanInvalidDataHexException;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class ProxyCallApiTest extends ApiRunner {

    @Test
    void correct() {
        Optional<String> call = getApi().proxy().call("0xAEEF46DB4855E25702F8237E8f403FddcaF931C0",
                "0x70a08231000000000000000000000000e16359506c028e51f16be38986ec5746251e9724");
        assertTrue(call.isPresent());
        assertFalse(BasicUtils.isNotHex(call.get()));
    }

    @Test
    void invalidParamWithError() {
        assertThrows(EtherScanInvalidAddressException.class,
                () -> getApi().proxy().call("0xEEF46DB4855E25702F8237E8f403FddcaF931C0",
                        "0x70a08231000000000000000000000000e16359506c028e51f16be38986ec5746251e9724"));
    }

    @Test
    void invalidParamNotHex() {
        assertThrows(EtherScanInvalidDataHexException.class,
                () -> getApi().proxy().call("0xAEEF46DB4855E25702F8237E8f403FddcaF931C0",
                        "7-0a08231000000000000000000000000e16359506c028e51f16be38986ec5746251e9724"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Optional<String> call = getApi().proxy().call("0xAEEF16DB4855E25702F8237E8f403FddcaF931C0",
                "0x70a08231000000000000000000000000e16359506c028e51f16be38986ec5746251e9724");
        assertTrue(call.isPresent());
        assertFalse(BasicUtils.isNotHex(call.get()), call.get());
    }
}
