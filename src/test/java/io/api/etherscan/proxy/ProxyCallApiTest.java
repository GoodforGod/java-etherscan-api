package io.api.etherscan.proxy;

import io.api.ApiRunner;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.error.InvalidDataHexException;
import io.api.etherscan.util.BasicUtils;
import org.junit.Test;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ProxyCallApiTest extends ApiRunner {

    @Test
    public void correct() {
        Optional<String> call = getApi().proxy().call("0xAEEF46DB4855E25702F8237E8f403FddcaF931C0",
                "0x70a08231000000000000000000000000e16359506c028e51f16be38986ec5746251e9724");
        assertTrue(call.isPresent());
        assertFalse(BasicUtils.isNotHex(call.get()));
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        Optional<String> call = getApi().proxy().call("0xEEF46DB4855E25702F8237E8f403FddcaF931C0",
                "0x70a08231000000000000000000000000e16359506c028e51f16be38986ec5746251e9724");
    }

    @Test(expected = InvalidDataHexException.class)
    public void invalidParamNotHex() {
        Optional<String> call = getApi().proxy().call("0xAEEF46DB4855E25702F8237E8f403FddcaF931C0",
                "7-0a08231000000000000000000000000e16359506c028e51f16be38986ec5746251e9724");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<String> call = getApi().proxy().call("0xAEEF16DB4855E25702F8237E8f403FddcaF931C0",
                "0x70a08231000000000000000000000000e16359506c028e51f16be38986ec5746251e9724");
        assertTrue(call.isPresent());
        assertFalse(BasicUtils.isNotHex(call.get()));
    }
}
