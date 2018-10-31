package io.api.core;

import io.api.error.ApiException;
import io.api.model.Abi;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#contracts
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public interface IContractProvider {

    /** Get Verified Contract Sources */
    @NotNull Abi contractAbi(String address) throws ApiException;
}
