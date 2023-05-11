package io.goodforgod.api.etherscan.proxy;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.error.EtherScanInvalidTxHashException;
import io.goodforgod.api.etherscan.model.proxy.TxProxy;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class ProxyTxApiTest extends ApiRunner {

    @Test
    void correctByHash() {
        Optional<TxProxy> tx = getApi().proxy().tx("0x1e2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
        assertTrue(tx.isPresent());
        assertNotNull(tx.get().getBlockHash());
        assertNotNull(tx.get().getFrom());
        assertNotNull(tx.get().getTo());
        assertNotNull(tx.get().getHash());
        assertNotNull(tx.get().getNonce());
        assertNotNull(tx.get().getBlockNumber());
        assertNotNull(tx.get().toString());

        TxProxy empty = new TxProxy();
        assertNotEquals(tx.get(), empty);
        assertNotEquals(tx.get().hashCode(), empty.hashCode());
    }

    @Test
    void correctByBlockNo() {
        Optional<TxProxy> tx = getApi().proxy().tx(637368, 0);
        assertTrue(tx.isPresent());
        assertNotNull(tx.get().getBlockHash());
        assertNotNull(tx.get().getFrom());
        assertNotNull(tx.get().getTo());
        assertNotNull(tx.get().getHash());
        assertNotNull(tx.get().getNonce());
        assertNotNull(tx.get().getS());
        assertNotNull(tx.get().getR());
        assertNotNull(tx.get().getValue());
        assertNotNull(tx.get().getV());
        assertNotNull(tx.get().getGas());
        assertNotNull(tx.get().getGasPrice());
        assertNotNull(tx.get().getBlockHash());
        assertNotNull(tx.get().getTransactionIndex());
        assertNotNull(tx.get().getInput());
    }

    @Test
    void invalidParamWithError() {
        assertThrows(EtherScanInvalidTxHashException.class,
                () -> getApi().proxy().tx("0xe2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1"));
    }

    @Test
    void correctParamWithEmptyExpectedResultBlockNoExist() {
        Optional<TxProxy> tx = getApi().proxy().tx(99999999L, 0);
        assertFalse(tx.isPresent());
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Optional<TxProxy> tx = getApi().proxy().tx("0x2e2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
        assertFalse(tx.isPresent());
    }
}
