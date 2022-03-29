package io.api.etherscan.account;

import io.api.ApiRunner;
import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidTxHashException;
import io.api.etherscan.model.TxInternal;
import io.api.etherscan.util.BasicUtils;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class AccountTxInternalByHashTest extends ApiRunner {

    private final EtherScanApi api = getApi();

    @Test
    void correct() {
        List<TxInternal> txs = api.account()
                .txsInternalByHash("0x1b513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b");
        assertNotNull(txs);
        assertEquals(1, txs.size());
        assertTxs(txs);
        assertNotNull(txs.get(0).getFrom());
        assertNotNull(txs.get(0).getTimeStamp());
        assertNotNull(txs.get(0).getGas());
        assertNotNull(txs.get(0).getValue());
        assertNotNull(txs.get(0).getType());
        assertFalse(txs.get(0).haveError());
        assertFalse(txs.get(0).haveError());
        assertNotEquals("-1", txs.get(0).getTraceIdAsString());
        assertTrue(BasicUtils.isEmpty(txs.get(0).getErrCode()));
        assertNotNull(txs.get(0).toString());

        if (txs.size() > 9) {
            assertNotEquals(txs.get(0), txs.get(9));
            assertNotEquals(txs.get(0).hashCode(), txs.get(9).hashCode());
        }
    }

    @Test
    void invalidParamWithError() {
        assertThrows(InvalidTxHashException.class,
                () -> api.account().txsInternalByHash("0xb513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        List<TxInternal> txs = api.account()
                .txsInternalByHash("0x2b513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b");
        assertNotNull(txs);
        assertTrue(txs.isEmpty());
    }

    private void assertTxs(List<TxInternal> txs) {
        for (TxInternal tx : txs) {
            assertNotEquals(0, tx.getBlockNumber());
            assertNotNull(tx.getFrom());
            assertNotNull(tx.getTo());
            assertNotNull(tx.getTimeStamp());
        }
    }
}
