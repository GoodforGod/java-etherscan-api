package io.api.etherscan.account;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Balance;
import io.api.support.AddressUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class AccountBalanceListTest extends Assert {

    private EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        List<String> addresses = new ArrayList<>();
        addresses.add("0x9327cb34984c3992ec1EA0eAE98Ccf80A74f95B9");
        addresses.add("0xC9F32CE1127e44C51cbD182D6364F3D707Fd0d47");

        List<Balance> balances = api.account().balances(addresses);
        assertNotNull(balances);
        assertFalse(balances.isEmpty());
        assertEquals(2, balances.size());
        assertNotEquals(balances.get(0), balances.get(1));
        assertNotEquals(balances.get(0).hashCode(), balances.get(1).hashCode());
        for(Balance balance : balances) {
            assertNotNull(balance.getAddress());
            assertNotNull(balance.getGwei());
            assertNotNull(balance.getKwei());
            assertNotNull(balance.getMwei());
            assertNotNull(balance.getEther());
            assertNotNull(balance.getGwei());
            assertNotNull(balance.getAddress());
            assertNotEquals(0, balance.getWei());
            assertNotNull(balance.toString());
        }
    }

    @Test
    public void correctMoreThat20Addresses() {
        List<String> addresses = AddressUtil.genRealAddresses();

        List<Balance> balances = api.account().balances(addresses);
        assertNotNull(balances);
        assertFalse(balances.isEmpty());
        assertEquals(25, balances.size());
        for(Balance balance : balances) {
            assertNotNull(balance.getAddress());
            assertNotEquals(0, balance.getWei());
            assertNotEquals(0, balance.getKwei());
            assertNotEquals(0, balance.getMwei());
            assertNotEquals(0, balance.getEther());
            assertNotEquals(0, balance.getGwei());
        }

        assertFalse(balances.get(0).equals(balances.get(1)));
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        List<String> addresses = new ArrayList<>();
        addresses.add("0x9327cb34984c3992ec1EA0eAE98Ccf80A74f95B9");
        addresses.add("C9F32CE1127e44C51cbD182D6364F3D707Fd0d47");

        List<Balance> balances = api.account().balances(addresses);
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        List<String> addresses = new ArrayList<>();
        addresses.add("0x1327cb34984c3992ec1EA0eAE98Ccf80A74f95B9");
        addresses.add("0xC1F32CE1127e44C51cbD182D6364F3D707Fd0d47");

        List<Balance> balances = api.account().balances(addresses);
        assertNotNull(balances);
        assertFalse(balances.isEmpty());
        assertEquals(2, balances.size());
        for(Balance balance : balances) {
            assertNotNull(balance.getAddress());
            assertEquals(0, balance.getWei().intValue());
        }
    }
}
