package io.api.etherscan.account;

import io.api.ApiRunner;
import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Balance;
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
public class AccountBalanceTest extends ApiRunner {

    private final EtherScanApi api;
    private final String addressCorrect;
    private final String addressInvalid;
    private final String addressNoResponse;

    public AccountBalanceTest(EtherScanApi api, String addressCorrect, String addressInvalid, String addressNoResponse) {
        this.api = api;
        this.addressCorrect = addressCorrect;
        this.addressInvalid = addressInvalid;
        this.addressNoResponse = addressNoResponse;
    }

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {
                        getApi(),
                        "0x8d4426f94e42f721C7116E81d6688cd935cB3b4F",
                        "8d4426f94e42f721C7116E81d6688cd935cB3b4F",
                        "0x1d4426f94e42f721C7116E81d6688cd935cB3b4F"
                }
        });
    }

    @Test
    public void correct() {
        Balance balance = api.account().balance(addressCorrect);
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getMwei());
        assertNotNull(balance.getKwei());
        assertNotNull(balance.getGwei());
        assertNotNull(balance.getEther());
        assertNotNull(balance.getAddress());
        assertNotNull(balance.toString());
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        Balance balance = getApi().account().balance(addressInvalid);
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Balance balance = api.account().balance(addressNoResponse);
        assertNotNull(balance);
        assertNotNull(balance.getWei());
        assertNotNull(balance.getAddress());
        assertEquals(0, balance.getWei().intValue());
    }
}
