package io.api.etherscan.contract;

import io.api.ApiRunner;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.Abi;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class ContractApiTest extends ApiRunner {

    @Test
    void correct() {
        Abi abi = getApi().contract().contractAbi("0xBB9bc244D798123fDe783fCc1C72d3Bb8C189413");
        assertNotNull(abi);
        assertTrue(abi.isVerified());
        assertTrue(abi.haveAbi());
        assertNotNull(abi.getContractAbi());
        assertNotNull(abi.toString());

        Abi empty = Abi.verified("asg");
        assertNotEquals(empty, abi);
        assertNotEquals(empty.hashCode(), abi.hashCode());
    }

    @Test
    void invalidParamWithError() {
        assertThrows(InvalidAddressException.class,
                () -> getApi().contract().contractAbi("0xBBbc244D798123fDe783fCc1C72d3Bb8C189413"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Abi abi = getApi().contract().contractAbi("0xBB9bc244D798123fDe783fCc1C72d3Bb8C189413");
        assertNotNull(abi);
        assertTrue(abi.isVerified());
    }
}
