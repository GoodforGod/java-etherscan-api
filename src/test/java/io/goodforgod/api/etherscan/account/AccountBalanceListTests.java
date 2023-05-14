package io.goodforgod.api.etherscan.account;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.error.EtherScanInvalidAddressException;
import io.goodforgod.api.etherscan.model.Balance;
import io.goodforgod.api.etherscan.support.AddressUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class AccountBalanceListTests extends ApiRunner {

    @Test
    void correct() {
        List<String> addresses = new ArrayList<>();
        addresses.add("0x9327cb34984c3992ec1EA0eAE98Ccf80A74f95B9");
        addresses.add("0xC9F32CE1127e44C51cbD182D6364F3D707Fd0d47");

        List<Balance> balances = getApi().account().balances(addresses);
        assertNotNull(balances);
        assertFalse(balances.isEmpty());
        assertEquals(2, balances.size());
        assertNotEquals(balances.get(0), balances.get(1));
        assertNotEquals(balances.get(0).hashCode(), balances.get(1).hashCode());
        for (Balance balance : balances) {
            assertNotNull(balance.getAddress());
            assertNotNull(balance.getGwei());
            assertNotNull(balance.getKwei());
            assertNotNull(balance.getMwei());
            assertNotNull(balance.getEther());
            assertNotNull(balance.getGwei());
            assertNotNull(balance.getAddress());
            assertNotEquals(BigInteger.ZERO, balance.getWei());
            assertNotNull(balance.toString());
        }
    }

    @Test
    void correctMoreThat20Addresses() {
        List<String> addresses = AddressUtil.genRealAddresses();

        List<Balance> balances = getApi().account().balances(addresses);
        assertNotNull(balances);
        assertFalse(balances.isEmpty());
        assertEquals(25, balances.size());
        for (Balance balance : balances) {
            assertNotNull(balance.getAddress());
        }

        assertNotEquals(balances.get(0), balances.get(1));
    }

    @Test
    void invalidParamWithError() {
        List<String> addresses = new ArrayList<>();
        addresses.add("0x9327cb34984c3992ec1EA0eAE98Ccf80A74f95B9");
        addresses.add("C9F32CE1127e44C51cbD182D6364F3D707Fd0d47");

        assertThrows(EtherScanInvalidAddressException.class, () -> getApi().account().balances(addresses));
    }

    @Test
    void emptyParamList() {
        List<String> addresses = new ArrayList<>();
        List<Balance> balances = getApi().account().balances(addresses);
        assertNotNull(balances);
        assertTrue(balances.isEmpty());
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        List<String> addresses = new ArrayList<>();
        addresses.add("0x1327cb34984c3992ec1EA0eAE98Ccf80A74f95B9");
        addresses.add("0xC1F32CE1127e44C51cbD182D6364F3D707Fd0d47");

        List<Balance> balances = getApi().account().balances(addresses);
        assertNotNull(balances);
        assertFalse(balances.isEmpty());
        assertEquals(2, balances.size());
        for (Balance balance : balances) {
            assertNotNull(balance.getAddress());
            assertEquals(0, balance.getWei().intValue());
        }
    }
}
