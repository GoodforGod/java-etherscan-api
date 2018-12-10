package io.api.etherscan.account;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
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
public class AccountTxInternalTest extends Assert {

    private EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        List<TxInternal> txs = api.account().txsInternal("0x2C1ba59D6F58433FB1EaEe7d20b26Ed83bDA51A3");
        assertNotNull(txs);
        assertEquals(66, txs.size());
        assertTxs(txs);
        assertNotNull(txs.get(0).toString());
    }

    @Test
    public void correctStartBlock() {
        List<TxInternal> txs = api.account().txsInternal("0x2C1ba59D6F58433FB1EaEe7d20b26Ed83bDA51A3", 2558775);
        assertNotNull(txs);
        assertEquals(24, txs.size());
        assertTxs(txs);
    }

    @Test
    public void correctStartBlockEndBlock() {
        List<TxInternal> txs = api.account().txsInternal("0x2C1ba59D6F58433FB1EaEe7d20b26Ed83bDA51A3", 2558775, 2685504);
        assertNotNull(txs);
        assertEquals(21, txs.size());
        assertTxs(txs);
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        List<TxInternal> txs = api.account().txsInternal("0x2C1ba59D6F58433FB1EaEe7d20b26Ed83bDA51");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        List<TxInternal> txs = api.account().txsInternal("0x2C1ba59D6F58433FB2EaEe7d20b26Ed83bDA51A3");
        assertNotNull(txs);
        assertTrue(txs.isEmpty());
    }

    private void assertTxs(List<TxInternal> txs) {
        for (TxInternal tx : txs) {
            assertNotNull(tx.getHash());
            assertNotNull(tx.getFrom());
            assertNotNull(tx.getTo());
            assertNotNull(tx.getTimeStamp());
        }
    }
}
