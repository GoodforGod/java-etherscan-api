package io.api.etherscan.account;

import io.api.ApiRunner;
import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.TokenBalance;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class AccountTokenBalanceTest extends ApiRunner {

    private final EtherScanApi api = getApi();

    @Test
    void correct() {
        TokenBalance balance = api.account().balance("0x5d807e7F124EC2103a59c5249187f772c0b8D6b2",
                "0x5EaC95ad5b287cF44E058dCf694419333b796123");
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getAddress());
        assertNotNull(balance.getContract());
        assertNotNull(balance.toString());

        TokenBalance balance2 = new TokenBalance("125161", balance.getWei(), balance.getContract());
        assertNotEquals(balance, balance2);
        assertNotEquals(balance.hashCode(), balance2.hashCode());
    }

    @Test
    void invalidAddressParamWithError() {
        assertThrows(InvalidAddressException.class, () -> api.account().balance("0x5807e7F124EC2103a59c5249187f772c0b8D6b2",
                "0x5EaC95ad5b287cF44E058dCf694419333b796123"));
    }

    @Test
    void invalidContractParamWithError() {
        assertThrows(InvalidAddressException.class, () -> api.account().balance("0x5d807e7F124EC2103a59c5249187f772c0b8D6b2",
                "0xEaC95ad5b287cF44E058dCf694419333b796123"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        TokenBalance balance = api.account().balance("0x1d807e7F124EC2103a59c5249187f772c0b8D6b2",
                "0x5EaC95ad5b287cF44E058dCf694419333b796123");
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getAddress());
        assertNotNull(balance.getContract());
        assertEquals(0, balance.getWei().intValue());
    }
}
