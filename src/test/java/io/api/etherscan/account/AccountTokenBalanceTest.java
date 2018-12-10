package io.api.etherscan.account;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Balance;
import io.api.etherscan.model.EthNetwork;
import io.api.etherscan.model.TokenBalance;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
@RunWith(Parameterized.class)
public class AccountTokenBalanceTest extends Assert {

    private EtherScanApi api;
    private String contractValid;
    private String addressValid;
    private String contractInvalid;
    private String addressInvalid;
    private String addressEmpty;

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
        return Arrays.asList(new Object[][]{
                {
                        new EtherScanApi(),
                        "0x5EaC95ad5b287cF44E058dCf694419333b796123",
                        "0x5d807e7F124EC2103a59c5249187f772c0b8D6b2",
                        "0xEaC95ad5b287cF44E058dCf694419333b796123",
                        "0x5807e7F124EC2103a59c5249187f772c0b8D6b2",
                        "0x1d807e7F124EC2103a59c5249187f772c0b8D6b2",
                },
                {
                        new EtherScanApi(EthNetwork.ROPSTEN),
                        "0x60a5aa08619bd5f71c6d20bfaefb5ac2c2806745",
                        "0x0923dafeb5a5d11a83e188d5dbcded14f9b161a7",
                        "0x0a5aa08619bd5f71c6d20bfaefb5ac2c2806745",
                        "0x923dafeb5a5d11a83e188d5dbcded14f9b161a7",
                        "0x1923dafeb5a5d11a83e188d5dbcded14f9b161a7",
                },
                {
                        new EtherScanApi(EthNetwork.RINKEBY),
                        "0xb8b6f3fb67403c90652dc5f085ba4a62ab1ef5ce",
                        "0x7ffc57839b00206d1ad20c69a1981b489f772031",
                        "0x8b6f3fb67403c90652dc5f085ba4a62ab1ef5ce",
                        "0x7fc57839b00206d1ad20c69a1981b489f772031",
                        "0x1ffc57839b00206d1ad20c69a1981b489f772031",
                },
                {
                        new EtherScanApi(EthNetwork.KOVAN),
                        "0xde0eaa632f071069214f1c1ad7eb609ff8152dfe",
                        "0x00e6d2b931f55a3f1701c7389d592a7778897879",
                        "0xd0eaa632f071069214f1c1ad7eb609ff8152dfe",
                        "0x0e6d2b931f55a3f1701c7389d592a7778897879",
                        "0x10e6d2b931f55a3f1701c7389d592a7778897879",
                },
        });
    }

    @Test
    public void correct() {
        TokenBalance balance = api.account().balance(addressValid, contractValid);
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getAddress());
        assertNotNull(balance.getContract());
        assertNotEquals(0, balance.getWei());
        assertNotNull(balance.toString());

        TokenBalance balance1 = new TokenBalance("", BigInteger.ONE, "");
        assertFalse(balance.equals(balance1));
        assertFalse(balance.hashCode() == balance1.hashCode());

        TokenBalance balance2 = new TokenBalance("125161", balance.getWei(), balance.getContract());
        assertFalse(balance.equals(balance2));
        assertFalse(balance.hashCode() == balance2.hashCode());
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
