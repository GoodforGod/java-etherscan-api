package io.api.etherscan.transaction;

import io.api.ApiRunner;
import io.api.etherscan.error.InvalidTxHashException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class TransactionReceiptApiTest extends ApiRunner {

    @Test
    void correct() {
        Optional<Boolean> status = getApi().txs()
                .receiptStatus("0x513c1ba0bebf66436b5fed86ab668452b7805593c05073eb2d51d3a52f480a76");
        assertTrue(status.isPresent());
        assertTrue(status.get());
    }

    @Test
    void invalidParamWithError() {
        assertThrows(InvalidTxHashException.class,
                () -> getApi().txs().receiptStatus("0x13c1ba0bebf66436b5fed86ab668452b7805593c05073eb2d51d3a52f480a76"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Optional<Boolean> status = getApi().txs()
                .receiptStatus("0x113c1ba0bebf66436b5fed86ab668452b7805593c05073eb2d51d3a52f480a76");
        assertFalse(status.isPresent());
    }
}
