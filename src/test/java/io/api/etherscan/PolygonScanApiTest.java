package io.api.etherscan;

import io.api.etherscan.core.impl.PolygonScanApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.error.ApiKeyException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.executor.impl.HttpExecutor;
import io.api.etherscan.model.*;
import io.api.etherscan.model.network.PolygonNetwork;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class PolygonScanApiTest extends Assert {

    private static final String RANDOM_ACCOUNT_1 = "0xDD7CF7Ae4E367eccb90c0f81F5Dbfd72b1FFfFdC";
    private static final String RANDOM_ACCOUNT_2 = "0x0EB6e8db98Fb33e7027A18A07f4E552cb44CDA0b";

    private final PolygonNetwork network = PolygonNetwork.TESTNET;
    private final String validKey = "YourKey";

    @Test
    public void validKey() {
        PolygonScanApi api = new PolygonScanApi(validKey, network);
        assertNotNull(api);
    }

    @Test(expected = ApiKeyException.class)
    public void emptyKey() {
        new PolygonScanApi("");
    }

    @Test(expected = ApiKeyException.class)
    public void blankKey() {
        new PolygonScanApi("         ", network);
    }

    @Test(expected = ApiException.class)
    public void nullNetwork() {
        PolygonScanApi api = new PolygonScanApi(validKey, null);
        assertNotNull(api);
    }

    @Test
    public void noTimeoutOnRead() {
        Supplier<IHttpExecutor> supplier = () -> new HttpExecutor(300);
        PolygonScanApi api = new PolygonScanApi(PolygonNetwork.MAINNET, supplier);
        Balance balance = api.account().balance("0xF318ABc9A5a92357c4Fea8d082dade4D43e780B7");
        assertNotNull(balance);
    }

    @Test
    public void accounts_singleAddress() {
        Balance balance1 = new PolygonScanApi().account().balance(RANDOM_ACCOUNT_1);
        assertNotNull(balance1);
        assertNotNull(balance1.getWei());

        Balance balance2 = new PolygonScanApi().account().balance(RANDOM_ACCOUNT_2);
        assertNotNull(balance2);
        assertNotNull(balance2.getWei());
    }

    @Test
    public void accounts_multpleAddresses() {
        List<Balance> balances = new PolygonScanApi().account().balances(Arrays.asList(RANDOM_ACCOUNT_1, RANDOM_ACCOUNT_2));
        assertEquals(2, balances.size());
        Balance balance1 = balances.get(0);
        assertNotNull(balance1);
        assertNotNull(balance1.getWei());
        Balance balance2 = balances.get(1);
        assertNotNull(balance2);
        assertNotNull(balance2.getWei());
    }

    @Test
    public void accounts_normalTransactions() {
        List<Tx> txs = new PolygonScanApi().account().txs(RANDOM_ACCOUNT_1);
        assertFalse(txs.isEmpty());
    }

    @Test
    public void accounts_internalTransactions() {
        List<TxInternal> txInternals = new PolygonScanApi().account().txsInternal(RANDOM_ACCOUNT_2);
        assertFalse(txInternals.isEmpty());
    }

    @Test
    public void accounts_internalTransactionsByHash() {
        String hash = "0x8c418e9f1a9c565ecf5304d17179b0aa4b63351011d8dbaf15ccdf57ce9124b0";
        List<TxInternal> txInternals = new PolygonScanApi().account().txsInternalByHash(hash);
        assertFalse(txInternals.isEmpty());
    }

    @Test
    public void accountsTokenTransactions() {
        List<TxToken> txTokens = new PolygonScanApi().account().txsToken(RANDOM_ACCOUNT_1);
        assertFalse(txTokens.isEmpty());
    }

    @Test
    public void accounts_NftTransactions() {
        List<TxToken> txTokens = new PolygonScanApi().account().txsNftToken(RANDOM_ACCOUNT_1);
        assertFalse(txTokens.isEmpty());
    }

    @Test
    public void contracts_abi() {
        String miMaticAddress = "0xa3fa99a148fa48d14ed51d610c367c61876997f1";
        Abi abi = new PolygonScanApi().contract().contractAbi(miMaticAddress);
        assertNotNull(abi);
        assertTrue(abi.isVerified());
    }
}
