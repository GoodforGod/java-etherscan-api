package io.api.etherscan.core;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.model.Abi;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions https://etherscan.io/apis#contracts
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public interface IContractApi {

    /**
     * Get Verified Contract Sources
     * 
     * @param address to verify
     * @return ABI verified
     * @throws ApiException parent exception class
     */
    @NotNull
    Abi contractAbi(String address) throws ApiException;
}
