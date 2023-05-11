package io.goodforgod.api.etherscan.transaction;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.error.EtherScanInvalidTxHashException;
import io.goodforgod.api.etherscan.model.Status;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class TransactionExecApiTest extends ApiRunner {

    @Test
    void correct() {
        Optional<Status> status = getApi().txs().statusExec("0x15f8e5ea1079d9a0bb04a4c58ae5fe7654b5b2b4463375ff7ffb490aa0032f3a");
        assertTrue(status.isPresent());
        assertTrue(status.get().haveError());
        assertNotNull(status.get().getErrDescription());
        assertNotNull(status.get().toString());

        Status empty = new Status();
        assertNotEquals(empty, status.get());
        assertNotEquals(empty.hashCode(), status.get().hashCode());
    }

    @Test
    void invalidParamWithError() {
        assertThrows(EtherScanInvalidTxHashException.class,
                () -> getApi().txs().statusExec("0xb513dd971aad228eb31f54489803639de167309ac72de68ecdaeb022a7ab42b"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Optional<Status> status = getApi().txs().statusExec("0x55f8e5ea1079d9a0bb04a4c58ae5fe7654b5b2b4463375ff7ffb490aa0032f3a");
        assertTrue(status.isPresent());
        assertFalse(status.get().haveError());
    }
}
