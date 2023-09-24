package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.model.Abi;
import io.goodforgod.api.etherscan.model.ContractCreation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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
    Abi contractAbi(@NotNull String address) throws EtherScanException;

    /**
     * Returns a contract's deployer address and transaction hash it was created, up to 5 at a time.
     * @param contractAddresses - list of addresses to fetch
     * @throws EtherScanException parent exception class
     */
    @NotNull
    List<ContractCreation> contractCreation(@NotNull List<String> contractAddresses) throws EtherScanException;
}
