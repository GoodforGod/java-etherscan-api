package io.api.etherscan.proxy;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidTxHashException;
import io.api.etherscan.model.proxy.ReceiptProxy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ProxyTxReceiptApiTest extends Assert{

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        Optional<ReceiptProxy> infoProxy = api.proxy().txReceipt("0x1e2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
        assertTrue(infoProxy.isPresent());
    }

    @Test(expected = InvalidTxHashException.class)
    public void invalidParamWithError() {
        Optional<ReceiptProxy> infoProxy = api.proxy().txReceipt("0xe2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<ReceiptProxy> infoProxy = api.proxy().txReceipt("0x2e2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
        assertFalse(infoProxy.isPresent());
    }
}
