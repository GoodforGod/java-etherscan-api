package io.api.etherscan.account;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Balance;
import io.api.etherscan.model.EthNetwork;
import org.junit.Assert;
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
public class AccountBalanceTest extends Assert {

    private EtherScanApi api;
    private String addressCorrect;
    private String addressInvalid;
    private String addressNoResponse;

    public AccountBalanceTest(EtherScanApi api, String addressCorrect, String addressInvalid, String addressNoResponse) {
        this.api = api;
        this.addressCorrect = addressCorrect;
        this.addressInvalid = addressInvalid;
        this.addressNoResponse = addressNoResponse;
    }

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {
                        new EtherScanApi(),
                        "0x8d4426f94e42f721C7116E81d6688cd935cB3b4F",
                        "8d4426f94e42f721C7116E81d6688cd935cB3b4F",
                        "0x1d4426f94e42f721C7116E81d6688cd935cB3b4F"
                },
                {
                        new EtherScanApi(EthNetwork.ROPSTEN),
                        "0xddbd2b932c763ba5b1b7ae3b362eac3e8d40121a",
                        "xddbd2b932c763ba5b1b7ae3b362eac3e8d40121a",
                        "0x1dbd2b932c763ba5b1b7ae3b362eac3e8d40121a"
                },
                {
                        new EtherScanApi(EthNetwork.RINKEBY),
                        "0xddbd2b932c763ba5b1b7ae3b362eac3e8d40121a",
                        "xddbd2b932c763ba5b1b7ae3b362eac3e8d40121a",
                        "0x1dbd2b932c763ba5b1b7ae3b362eac3e8d40121a"
                },
                {
                        new EtherScanApi(EthNetwork.KOVAN),
                        "0xB9F36EE9df7E2A24B61b1738F4127BFDe8bA1A87",
                        "xB9F36EE9df7E2A24B61b1738F4127BFDe8bA1A87",
                        "0xB1F36EE9df7E2A24B61b1738F4127BFDe8bA1A87"
                },
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
        assertNotEquals(0, balance.getWei());
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        Balance balance = api.account().balance(addressInvalid);
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
