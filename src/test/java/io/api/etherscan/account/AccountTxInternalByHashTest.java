package io.api.etherscan.account;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidTxHashException;
import io.api.etherscan.model.EthNetwork;
import io.api.etherscan.model.TxInternal;
import io.api.etherscan.util.BasicUtils;
import org.junit.Assert;
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
public class AccountTxInternalByHashTest extends Assert {

    private EtherScanApi api;
    private int txAmount;
    private String validTx;
    private String invalidTx;
    private String emptyTx;

    public AccountTxInternalByHashTest(EtherScanApi api, int txAmount, String validTx, String invalidTx, String emptyTx) {
        this.api = api;
        this.txAmount = txAmount;
        this.validTx = validTx;
        this.invalidTx = invalidTx;
        this.emptyTx = emptyTx;
    }

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {
                        new EtherScanApi(),
                        1,
                        "0x1b513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b",
                        "0xb513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b",
                        "0x2b513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b",
                },
                {
                        new EtherScanApi(EthNetwork.ROPSTEN),
                        1,
                        "0x8bc5504517d40ad7b4f32d008d82436cc118e856593f61d23c70eec73c10322a",
                        "0x8c5504517d40ad7b4f32d008d82436cc118e856593f61d23c70eec73c10322a",
                        "0x7bc5504517d40ad7b4f32d008d82436cc118e856593f61d23c70eec73c10322a",
                },
                {
                        new EtherScanApi(EthNetwork.RINKEBY),
                        10,
                        "0x4be697e735f594038e2e70e051948896bdfd6193fc399e38d6113e96f96e8567",
                        "0x4e697e735f594038e2e70e051948896bdfd6193fc399e38d6113e96f96e8567",
                        "0x3be697e735f594038e2e70e051948896bdfd6193fc399e38d6113e96f96e8567",
                },
                {
                        new EtherScanApi(EthNetwork.KOVAN),
                        1,
                        "0x3b85c47f2a8c5efd639a85f5233097af834c9ab6ab7965cf7cfc3ab3c8bae285",
                        "0x385c47f2a8c5efd639a85f5233097af834c9ab6ab7965cf7cfc3ab3c8bae285",
                        "0x2b85c47f2a8c5efd639a85f5233097af834c9ab6ab7965cf7cfc3ab3c8bae285",
                },
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
        assertTrue(BasicUtils.isEmpty(txs.get(0).getErrCode()));
        assertNotNull(txs.get(0).toString());
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
