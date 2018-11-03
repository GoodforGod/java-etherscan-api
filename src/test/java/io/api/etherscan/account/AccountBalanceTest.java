package io.api.etherscan.account;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Balance;
import org.junit.Assert;
import org.junit.Test;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class AccountBalanceTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        Balance balance = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getMwei());
        assertNotNull(balance.getKwei());
        assertNotNull(balance.getGwei());
        assertNotNull(balance.getEther());
        assertNotNull(balance.getAddress());
        assertNotEquals(0, balance.getWei());
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        Balance balance = api.account().balance("8d4426f94e42f721C7116E81d6688cd935cB3b4F");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Balance balance = api.account().balance("0x8d4426f94e42f722C7116E81d6688cd935cB3b4F");
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getAddress());
        assertEquals(0, balance.getWei().intValue());
    }
}
