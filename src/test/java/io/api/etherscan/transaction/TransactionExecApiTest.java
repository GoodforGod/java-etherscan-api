package io.api.etherscan.transaction;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidTxHashException;
import io.api.etherscan.model.Status;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class TransactionExecApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        Optional<Status> status = api.txs().execStatus("0x15f8e5ea1079d9a0bb04a4c58ae5fe7654b5b2b4463375ff7ffb490aa0032f3a");
        assertTrue(status.isPresent());
        assertTrue(status.get().haveError());
        assertNotNull(status.get().getErrDescription());
        assertNotNull(status.get().toString());
    }

    @Test(expected = InvalidTxHashException.class)
    public void invalidParamWithError() {
        Optional<Status> status = api.txs().execStatus("0xb513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<Status> status = api.txs().execStatus("0x55f8e5ea1079d9a0bb04a4c58ae5fe7654b5b2b4463375ff7ffb490aa0032f3a");
        assertTrue(status.isPresent());
        assertFalse(status.get().haveError());
    }
}
