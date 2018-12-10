package io.api.etherscan.contract;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Abi;
import org.junit.Assert;
import org.junit.Test;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ContractApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        Abi abi = api.contract().contractAbi("0xBB9bc244D798123fDe783fCc1C72d3Bb8C189413");
        assertNotNull(abi);
        assertTrue(abi.isVerified());
        assertTrue(abi.haveAbi());
        assertNotNull(abi.getContractAbi());
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        Abi abi = api.contract().contractAbi("0xBBbc244D798123fDe783fCc1C72d3Bb8C189413");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Abi abi = api.contract().contractAbi("0xBB1bc244D798123fDe783fCc1C72d3Bb8C189413");
        assertNotNull(abi);
        assertFalse(abi.isVerified());
    }
}
