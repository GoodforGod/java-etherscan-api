package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.model.Abi;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions <a href="https://docs.etherscan.io/api-endpoints/contracts">...</a>
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public interface ContractAPI {

    /**
     * Get Verified Contract Sources
     * 
     * @param address to verify
     * @return ABI verified
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Abi contractAbi(String address) throws EtherScanException;
}
