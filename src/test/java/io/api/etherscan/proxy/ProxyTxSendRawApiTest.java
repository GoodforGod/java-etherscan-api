package io.api.etherscan.proxy;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.error.InvalidDataHexException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
//TODO contact etherscan and ask about methods
public class ProxyTxSendRawApiTest extends Assert{

    private final EtherScanApi api = new EtherScanApi();

    public void correct() {
        Optional<String> sendRaw = api.proxy().txSendRaw("0x1e2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
        assertTrue(sendRaw.isPresent());
    }

    @Test(expected = InvalidDataHexException.class)
    public void invalidParamWithError() {
        Optional<String> sendRaw = api.proxy().txSendRaw("5151=0561");
    }

    @Test(expected = EtherScanException.class)
    public void invalidParamEtherScanDataException() {
        Optional<String> sendRaw = api.proxy().txSendRaw("0x0");
    }

    public void correctParamWithEmptyExpectedResult() {
        Optional<String> sendRaw = api.proxy().txSendRaw("0x012151");
        assertFalse(sendRaw.isPresent());
    }
}
