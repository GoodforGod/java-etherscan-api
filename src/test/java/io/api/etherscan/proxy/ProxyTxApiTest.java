package io.api.etherscan.proxy;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidTxHashException;
import io.api.etherscan.model.proxy.TxProxy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ProxyTxApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correctByHash() {
        Optional<TxProxy> tx = api.proxy().tx("0x1e2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
        assertTrue(tx.isPresent());
        assertNotNull(tx.get().getBlockHash());
        assertNotNull(tx.get().getFrom());
        assertNotNull(tx.get().getTo());
        assertNotNull(tx.get().getHash());
        assertNotNull(tx.get().getNonce());
    }

    @Test
    public void correctByBlockNo() {
        Optional<TxProxy> tx = api.proxy().tx(637368, 0);
        assertTrue(tx.isPresent());
        assertNotNull(tx.get().getBlockHash());
        assertNotNull(tx.get().getFrom());
        assertNotNull(tx.get().getTo());
        assertNotNull(tx.get().getHash());
        assertNotNull(tx.get().getNonce());
    }

    @Test(expected = InvalidTxHashException.class)
    public void invalidParamWithError() {
        Optional<TxProxy> tx = api.proxy().tx("0xe2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
    }

    @Test
    public void correctParamWithEmptyExpectedResultBlockNoExist() {
        Optional<TxProxy> tx = api.proxy().tx(99999999L, 0);
        assertFalse(tx.isPresent());
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<TxProxy> tx = api.proxy().tx("0x2e2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
        assertFalse(tx.isPresent());
    }
}
