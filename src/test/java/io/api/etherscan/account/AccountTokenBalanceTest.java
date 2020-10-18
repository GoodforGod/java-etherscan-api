package io.api.etherscan.account;

import io.api.ApiRunner;
import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Balance;
import io.api.etherscan.model.TokenBalance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
@RunWith(Parameterized.class)
public class AccountTokenBalanceTest extends ApiRunner {

    private final EtherScanApi api;
    private final String contractValid;
    private final String addressValid;
    private final String contractInvalid;
    private final String addressInvalid;
    private final String addressEmpty;

    public AccountTokenBalanceTest(EtherScanApi api,
                                   String contractValid,
                                   String addressValid,
                                   String contractInvalid,
                                   String addressInvalid,
                                   String addressEmpty) {
        this.api = api;
        this.contractValid = contractValid;
        this.addressValid = addressValid;
        this.contractInvalid = contractInvalid;
        this.addressInvalid = addressInvalid;
        this.addressEmpty = addressEmpty;
    }

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {
                        getApi(),
                        "0x5EaC95ad5b287cF44E058dCf694419333b796123",
                        "0x5d807e7F124EC2103a59c5249187f772c0b8D6b2",
                        "0xEaC95ad5b287cF44E058dCf694419333b796123",
                        "0x5807e7F124EC2103a59c5249187f772c0b8D6b2",
                        "0x1d807e7F124EC2103a59c5249187f772c0b8D6b2",
                }
        });
    }

    @Test
    public void correct() {
        TokenBalance balance = api.account().balance(addressValid, contractValid);
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getAddress());
        assertNotNull(balance.getContract());
        assertNotNull(balance.toString());

        TokenBalance balance2 = new TokenBalance("125161", balance.getWei(), balance.getContract());
        assertNotEquals(balance, balance2);
        assertNotEquals(balance.hashCode(), balance2.hashCode());
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidAddressParamWithError() {
        Balance balance = api.account().balance(addressInvalid, contractValid);
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidContractParamWithError() {
        Balance balance = api.account().balance(addressValid, contractInvalid);
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        TokenBalance balance = api.account().balance(addressEmpty, contractValid);
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getAddress());
        assertNotNull(balance.getContract());
        assertEquals(0, balance.getWei().intValue());
    }
}
