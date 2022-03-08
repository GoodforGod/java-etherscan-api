package io.api.etherscan.account;

import io.api.ApiRunner;
import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidTxHashException;
import io.api.etherscan.model.TxInternal;
import io.api.etherscan.util.BasicUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
@RunWith(Parameterized.class)
public class AccountTxInternalByHashTest extends ApiRunner {

    private final EtherScanApi api;
    private final int txAmount;
    private final String validTx;
    private final String invalidTx;
    private final String emptyTx;

    public AccountTxInternalByHashTest(EtherScanApi api, int txAmount, String validTx, String invalidTx, String emptyTx) {
        this.api = api;
        this.txAmount = txAmount;
        this.validTx = validTx;
        this.invalidTx = invalidTx;
        this.emptyTx = emptyTx;
    }

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {
                        getApi(),
                        1,
                        "0x1b513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b",
                        "0xb513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b",
                        "0x2b513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b",
                }
        });
    }

    @Test
    public void correct() {
        List<TxInternal> txs = api.account().txsInternalByHash(validTx);
        assertNotNull(txs);
        assertEquals(txAmount, txs.size());
        assertTxs(txs);
        assertNotNull(txs.get(0).getFrom());
        assertNotNull(txs.get(0).getTimeStamp());
        assertNotNull(txs.get(0).getGas());
        assertNotNull(txs.get(0).getValue());
        assertNotNull(txs.get(0).getType());
        assertFalse(txs.get(0).haveError());
        assertFalse(txs.get(0).haveError());
        assertNotEquals(-1, txs.get(0).getTraceId());
        assertNotEquals("-1", txs.get(0).getTraceIdAsString());
        assertTrue(BasicUtils.isEmpty(txs.get(0).getErrCode()));
        assertNotNull(txs.get(0).toString());

        if (txs.size() > 9) {
            assertNotEquals(txs.get(0), txs.get(9));
            assertNotEquals(txs.get(0).hashCode(), txs.get(9).hashCode());
        }
    }

    @Test(expected = InvalidTxHashException.class)
    public void invalidParamWithError() {
        List<TxInternal> txs = api.account().txsInternalByHash(invalidTx);
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        List<TxInternal> txs = api.account().txsInternalByHash(emptyTx);
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
