package io.goodforgod.api.etherscan.account;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.error.EtherScanInvalidAddressException;
import io.goodforgod.api.etherscan.model.Tx;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class AccountTxsTests extends ApiRunner {

    @Test
    void correct() {
        List<Tx> txs = getApi().account().txs("0x9327cb34984c3992ec1EA0eAE98Ccf80A74f95B9");
        assertNotNull(txs);
        assertEquals(5, txs.size());
        assertTxs(txs);
        assertNotNull(txs.get(0).getTimeStamp());
        assertNotNull(txs.get(0).getHash());
        assertNotNull(txs.get(0).getFrom());
        assertNotNull(txs.get(0).getTo());
        assertNotNull(txs.get(0).getBlockHash());
        assertNotNull(txs.get(0).getGas());
        assertNotNull(txs.get(0).getCumulativeGasUsed());
        assertNotNull(txs.get(0).getGasPrice());
        assertNotNull(txs.get(0).getValue());
        assertNotNull(txs.get(0).getContractAddress());
        assertNotNull(txs.get(0).getInput());
        assertNotNull(txs.get(0).toString());
        assertNotEquals(txs.get(0), txs.get(1));
        assertNotEquals(txs.get(0).hashCode(), txs.get(1).hashCode());
        assertEquals(txs.get(1), txs.get(1));
    }

    @Test
    void correctStartBlock() {
        List<Tx> txs = getApi().account().txs("0x9327cb34984c3992ec1EA0eAE98Ccf80A74f95B9", 3892842);
        assertNotNull(txs);
        assertEquals(4, txs.size());
        assertTxs(txs);
    }

    @Test
    void correctStartBlockEndBlock() {
        List<Tx> txs = getApi().account().txs("0x9327cb34984c3992ec1EA0eAE98Ccf80A74f95B9", 3892842, 3945741);
        assertNotNull(txs);
        assertEquals(3, txs.size());
        assertTxs(txs);
        assertNotEquals(txs.get(0), txs.get(1));
    }

    @Test
    void invalidParamWithError() {
        assertThrows(EtherScanInvalidAddressException.class,
                () -> getApi().account().txs("9327cb34984c3992ec1EA0eAE98Ccf80A74f95B9"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        List<Tx> txs = getApi().account().txs("0x9321cb34984c3992ec1EA0eAE98Ccf80A74f95B9");
        assertNotNull(txs);
        assertTrue(txs.isEmpty());
    }

    private void assertTxs(List<Tx> txs) {
        for (Tx tx : txs) {
            assertFalse(tx.haveError());
            assertNotNull(tx.getBlockHash());
            assertNotNull(tx.getFrom());
            assertNotNull(tx.getTo());
            assertNotNull(tx.getTimeStamp());
            assertNotEquals(-1, (tx.getNonce()));
            assertNotEquals(0, (tx.getTransactionIndex()));
            assertNotEquals(0, tx.getConfirmations());
            assertNotNull(tx.getTxreceipt_status());
        }
    }
}
