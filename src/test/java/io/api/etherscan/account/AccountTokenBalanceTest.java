package io.api.etherscan.account;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Balance;
import io.api.etherscan.model.TokenBalance;
import org.junit.Assert;
import org.junit.Test;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class AccountTokenBalanceTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        TokenBalance balance = api.account().balance("0x5d807e7F124EC2103a59c5249187f772c0b8D6b2", "0x5EaC95ad5b287cF44E058dCf694419333b796123");
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getAddress());
        assertNotNull(balance.getContract());
        assertNotEquals(0, balance.getWei());
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidAddressParamWithError() {
        Balance balance = api.account().balance("5d807e7F124EC2103a59c5249187f772c0b8D6b2", "0x5EaC95ad5b287cF44E058dCf694419333b796123");
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidContractParamWithError() {
        Balance balance = api.account().balance("0x5d807e7F124EC2103a59c5249187f772c0b8D6b2", "5EaC95ad5b287cF44E058dCf694419333b796123");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        TokenBalance balance = api.account().balance("0x5d807e7F124EC2103a51c5249187f772c0b8D6b2", "0x5EaC95ad5b287cF44E058dCf694419333b796123");
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getAddress());
        assertNotNull(balance.getContract());
        assertEquals(0, balance.getWei().intValue());
    }

    @Test
    public void errorStatusExpected() {

    }
}
