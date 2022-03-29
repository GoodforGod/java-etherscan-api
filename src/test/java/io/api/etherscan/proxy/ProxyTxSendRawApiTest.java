package io.api.etherscan.proxy;

import io.api.ApiRunner;
import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.error.InvalidDataHexException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
// TODO contact etherscan and ask about method behavior
class ProxyTxSendRawApiTest extends ApiRunner {

    void correct() {
        Optional<String> sendRaw = getApi().proxy()
                .txSendRaw("0x1e2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
        assertTrue(sendRaw.isPresent());
    }

    @Test
    void invalidParamWithError() {
        assertThrows(InvalidDataHexException.class, () -> getApi().proxy().txSendRaw("5151=0561"));
    }

    @Test
    void invalidParamEtherScanDataException() {
        assertThrows(EtherScanException.class, () -> getApi().proxy().txSendRaw("0x1"));
    }

    void correctParamWithEmptyExpectedResult() {
        Optional<String> sendRaw = getApi().proxy().txSendRaw("0x000000");
        assertFalse(sendRaw.isPresent());
    }
}
