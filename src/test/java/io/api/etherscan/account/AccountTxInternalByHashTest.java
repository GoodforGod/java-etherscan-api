package io.api.etherscan.account;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidTxHashException;
import io.api.etherscan.model.TxInternal;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class AccountTxInternalByHashTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        List<TxInternal> txs = api.account().txsInternalByHash("0x1b513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b");
        assertNotNull(txs);
        assertEquals(1, txs.size());
        assertTxs(txs);
        assertNotNull(txs.get(0).getFrom());
        assertNotNull(txs.get(0).getTimeStamp());
        assertNotNull(txs.get(0).getGas());
        assertNotNull(txs.get(0).getHash());
    }

    @Test(expected = InvalidTxHashException.class)
    public void invalidParamWithError() {
        List<TxInternal> txs = api.account().txsInternalByHash("01b513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        List<TxInternal> txs = api.account().txsInternalByHash("0x2b513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b");
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
