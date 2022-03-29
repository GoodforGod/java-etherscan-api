package io.api.etherscan.account;

import io.api.ApiRunner;
import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Balance;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class AccountBalanceTest extends ApiRunner {

    private final EtherScanApi api = getApi();

    @Test
    void correct() {
        Balance balance = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getMwei());
        assertNotNull(balance.getKwei());
        assertNotNull(balance.getGwei());
        assertNotNull(balance.getEther());
        assertNotNull(balance.getAddress());
        assertNotNull(balance.toString());
    }

    @Test
    void invalidParamWithError() {
        assertThrows(InvalidAddressException.class, () -> getApi().account().balance("8d4426f94e42f721C7116E81d6688cd935cB3b4F"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Balance balance = api.account().balance("0x1d4426f94e42f721C7116E81d6688cd935cB3b4F");
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getAddress());
        assertEquals(0, balance.getWei().intValue());
    }
}
