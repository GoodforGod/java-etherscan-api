package io.goodforgod.api.etherscan.account;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.EtherScanAPI;
import io.goodforgod.api.etherscan.error.EtherScanInvalidAddressException;
import io.goodforgod.api.etherscan.model.Balance;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class AccountBalanceTests extends ApiRunner {

    private final EtherScanAPI api = getApi();

    @Test
    void correct() {
        Balance balance = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        assertNotNull(balance);
        assertNotNull(balance.getBalanceInWei());
        assertNotNull(balance.getAddress());
        assertNotNull(balance.toString());
    }

    @Test
    void invalidParamWithError() {
        assertThrows(EtherScanInvalidAddressException.class,
                () -> getApi().account().balance("8d4426f94e42f721C7116E81d6688cd935cB3b4F"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Balance balance = api.account().balance("0x1d4426f94e42f721C7116E81d6688cd935cB3b4F");
        assertNotNull(balance);
        assertNotNull(balance.getBalanceInWei());
        assertNotNull(balance.getAddress());
        assertEquals(0, balance.getBalanceInWei().asWei().intValue());
    }
}
