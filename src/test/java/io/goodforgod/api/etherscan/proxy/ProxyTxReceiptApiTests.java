package io.goodforgod.api.etherscan.proxy;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.error.EtherScanInvalidTxHashException;
import io.goodforgod.api.etherscan.model.proxy.ReceiptProxy;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class ProxyTxReceiptApiTests extends ApiRunner {

    @Test
    void correct() {
        Optional<ReceiptProxy> infoProxy = getApi().proxy()
                .txReceipt("0x1e2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
        assertTrue(infoProxy.isPresent());
        assertNotNull(infoProxy.get().getBlockHash());
        assertNotNull(infoProxy.get().getRoot());
        assertNotNull(infoProxy.get().getFrom());
        assertNotNull(infoProxy.get().getTo());
        assertNotNull(infoProxy.get().getBlockNumber());
        assertNotNull(infoProxy.get().getBlockHash());
        assertNotNull(infoProxy.get().getTransactionHash());
        assertNotNull(infoProxy.get().getTransactionIndex());
        assertNotNull(infoProxy.get().getGasUsed());
        assertNotNull(infoProxy.get().getGasUsedCumulative());
        assertNotNull(infoProxy.get().getLogs());
        assertNotNull(infoProxy.get().getLogsBloom());
        assertNull(infoProxy.get().getContractAddress());
        assertNotNull(infoProxy.get().toString());

        ReceiptProxy empty = ReceiptProxy.builder().build();
        assertNotEquals(empty, infoProxy.get());
        assertNotEquals(empty.hashCode(), infoProxy.get().hashCode());
    }

    @Test
    void invalidParamWithError() {
        assertThrows(EtherScanInvalidTxHashException.class, () -> getApi().proxy()
                .txReceipt("0xe2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Optional<ReceiptProxy> infoProxy = getApi().proxy()
                .txReceipt("0x2e2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
        assertFalse(infoProxy.isPresent());
    }
}
