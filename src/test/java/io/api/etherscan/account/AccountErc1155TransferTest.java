package io.api.etherscan.account;

import io.api.ApiRunner;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.TxErc1155;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.List;

public class AccountErc1155TransferTest extends ApiRunner {

    @Test
    public void correct() {
        List<TxErc1155> txs = getApi().account().erc1155Transfers("0xE4C8324534C0C6bCA174Cd0F02fAC9889C36bA59");
        assertNotNull(txs);
        assertFalse(txs.isEmpty());
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
        List<TxErc1155> txs = getApi().account().erc1155Transfers("0xE4C8324534C0C6bCA174Cd0F02fAC9889C36bA59", 14275897);
        assertNotNull(txs);
        assertFalse(txs.isEmpty());
        assertTxs(txs);
    }

    @Test
    public void correctStartBlockEndBlock() {
        List<TxErc1155> txs = getApi().account().erc1155Transfers("0xE4C8324534C0C6bCA174Cd0F02fAC9889C36bA59", 14275897, 15148929);
        assertNotNull(txs);
        assertEquals(11, txs.size());
        assertTxs(txs);
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        getApi().account().erc1155Transfers("0x6ec53A8fBa6358d59B3C4476D82cc60A2B0FaD7");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        List<TxErc1155> txs = getApi().account().erc1155Transfers("0x31ec53A8fBa6358d59B3C4476D82cc60A2B0FaD7");
        assertNotNull(txs);
        assertTrue(txs.isEmpty());
    }

    private void assertTxs(List<TxErc1155> txs) {
        txs.forEach(this::asserTx);
    }

    private void asserTx(@NotNull TxErc1155 tx) {
        assertNotNull(tx.getBlockHash());
        assertNotNull(tx.getTokenName());
        assertNotNull(tx.getTokenSymbol());
        assertNotNull(tx.getFrom());
        assertNotNull(tx.getTo());
        assertNotNull(tx.getTimeStamp());
        assertNotNull(tx.getTokenID());
        assertNotNull(tx.getTokenValue());
        assertNotEquals(-1, (tx.getConfirmations()));
        assertNotNull(tx.getGasUsed());
        assertNotEquals(-1, tx.getCumulativeGasUsed());
        assertNotEquals(-1, tx.getTransactionIndex());
    }
}
