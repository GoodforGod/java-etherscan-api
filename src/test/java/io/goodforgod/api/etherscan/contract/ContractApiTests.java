package io.goodforgod.api.etherscan.contract;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.error.EtherScanInvalidAddressException;
import io.goodforgod.api.etherscan.model.Abi;
import io.goodforgod.api.etherscan.model.ContractCreation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class ContractApiTests extends ApiRunner {

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
        assertThrows(EtherScanInvalidAddressException.class,
                () -> getApi().contract().contractAbi("0xBBbc244D798123fDe783fCc1C72d3Bb8C189413"));
    }

    @Test
    void correctParamWithEmptyExpectedResult() {
        Abi abi = getApi().contract().contractAbi("0xBB9bc244D798123fDe783fCc1C72d3Bb8C189413");
        assertNotNull(abi);
        assertTrue(abi.isVerified());
    }

    @Test
    void correctContractCreation() {
        List<ContractCreation> contractCreations = getApi().contract()
                .contractCreation(Collections.singletonList("0xBB9bc244D798123fDe783fCc1C72d3Bb8C189413"));

        assertEquals(1, contractCreations.size());
        ContractCreation contractCreation = contractCreations.get(0);

        assertEquals("0xbb9bc244d798123fde783fcc1c72d3bb8c189413", contractCreation.getContractAddress());
        assertEquals("0x793ea9692ada1900fbd0b80fffec6e431fe8b391", contractCreation.getContractCreator());
        assertEquals("0xe9ebfecc2fa10100db51a4408d18193b3ac504584b51a4e55bdef1318f0a30f9", contractCreation.getTxHash());
    }

    @Test
    void correctMultipleContractCreation() {
        List<ContractCreation> contractCreations = getApi().contract().contractCreation(
                Arrays.asList("0xBB9bc244D798123fDe783fCc1C72d3Bb8C189413", "0x5EaC95ad5b287cF44E058dCf694419333b796123"));
        assertEquals(2, contractCreations.size());

        ContractCreation contractCreation1 = ContractCreation.builder()
                .withContractAddress("0xbb9bc244d798123fde783fcc1c72d3bb8c189413")
                .withContractCreator("0x793ea9692ada1900fbd0b80fffec6e431fe8b391")
                .withTxHash("0xe9ebfecc2fa10100db51a4408d18193b3ac504584b51a4e55bdef1318f0a30f9")
                .build();

        ContractCreation contractCreation2 = ContractCreation.builder()
                .withContractAddress("0x5eac95ad5b287cf44e058dcf694419333b796123")
                .withContractCreator("0x7c675b7450e878e5af8550b41df42d134674e61f")
                .withTxHash("0x79cdfec19e5a86d9022680a4d1c86d3d8cd76c21c01903a2f02c127a0a7dbfb3")
                .build();

        assertTrue(contractCreations.contains(contractCreation1));
        assertTrue(contractCreations.contains(contractCreation2));
    }

    @Test
    void contractCreationInvalidParamWithError() {
        assertThrows(EtherScanInvalidAddressException.class,
                () -> getApi().contract()
                        .contractCreation(Collections.singletonList("0xBBbc244D798123fDe783fCc1C72d3Bb8C189414")));
    }
}
