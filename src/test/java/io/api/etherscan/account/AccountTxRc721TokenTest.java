package io.api.etherscan.account;

import io.api.ApiRunner;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.TxErc721;
import org.junit.Test;

import java.util.List;

/**
 * @author NGuggs
 * @since 11.28.2021
 */
public class AccountTxRc721TokenTest extends ApiRunner {

    @Test
    public void correct() {
        List<TxErc721> txs = getApi().account().erc721Transfers("0x1a1ebe0d86f72884c3fd484ae1e796e08f8ffa67");
        assertNotNull(txs);
        assertEquals(16, txs.size());
        assertTxs(txs);
        assertNotEquals(0, txs.get(0).getGasPrice());
        assertNotEquals(-1, txs.get(0).getNonce());

        assertNotNull(txs.get(0).toString());
        assertNotEquals(txs.get(0).toString(), txs.get(1).toString());

        assertNotEquals(txs.get(0), txs.get(1));
        assertNotEquals(txs.get(0).hashCode(), txs.get(1).hashCode());

        assertEquals(txs.get(1), txs.get(1));
        assertEquals(txs.get(1).hashCode(), txs.get(1).hashCode());
    }

    @Test
    public void correctStartBlock() {
        List<TxErc721> txs = getApi().account().erc721Transfers("0x1a1ebe0d86f72884c3fd484ae1e796e08f8ffa67", 4762071);
        assertNotNull(txs);
        assertEquals(5, txs.size());
        assertTxs(txs);
    }

    @Test
    public void correctStartBlockEndBlock() {
        List<TxErc721> txs = getApi().account().erc721Transfers("0x1a1ebe0d86f72884c3fd484ae1e796e08f8ffa67", 4761862, 4761934);
        assertNotNull(txs);
        assertEquals(11, txs.size());
        assertTxs(txs);
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        getApi().account().erc721Transfers("0x6ec53A8fBa6358d59B3C4476D82cc60A2B0FaD7");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        List<TxErc721> txs = getApi().account().erc721Transfers("0x31ec53A8fBa6358d59B3C4476D82cc60A2B0FaD7");
        assertNotNull(txs);
        assertTrue(txs.isEmpty());
    }

    private void assertTxs(List<TxErc721> txs) {
        for (TxErc721 tx : txs) {
            assertNotNull(tx.getBlockHash());
            assertNotNull(tx.getTokenName());
            assertNotNull(tx.getTokenSymbol());
            assertNotNull(tx.getFrom());
            assertNotNull(tx.getTo());
            assertNotNull(tx.getTimeStamp());
            assertNotNull(tx.getTokenDecimal());
            assertNotEquals(-1, (tx.getConfirmations()));
            assertNotNull(tx.getGasUsed());
            assertNotEquals(-1, tx.getCumulativeGasUsed());
            assertNotEquals(-1, tx.getTransactionIndex());
        }
    }
}
