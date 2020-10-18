package io.api.etherscan.account;

import io.api.ApiRunner;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Balance;
import io.api.support.AddressUtil;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class AccountBalanceListTest extends ApiRunner {

    @Test
    public void correct() {
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
    public void correctMoreThat20Addresses() {
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

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        List<String> addresses = new ArrayList<>();
        addresses.add("0x9327cb34984c3992ec1EA0eAE98Ccf80A74f95B9");
        addresses.add("C9F32CE1127e44C51cbD182D6364F3D707Fd0d47");

        getApi().account().balances(addresses);
    }

    @Test
    public void emptyParamList() {
        List<String> addresses = new ArrayList<>();
        List<Balance> balances = getApi().account().balances(addresses);
        assertNotNull(balances);
        assertTrue(balances.isEmpty());
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
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
