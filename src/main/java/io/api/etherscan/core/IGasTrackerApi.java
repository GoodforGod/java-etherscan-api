package io.api.etherscan.core;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.model.GasOracle;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions https://docs.etherscan.io/api-endpoints/gas-tracker
 *
 * @author Abhay Gupta
 * @since 14.11.2022
 */
public interface IGasTrackerApi {

    /**
     * GasOracle details
     *
     * @return fast, suggested gas price
     * @throws ApiException parent exception class
     */
    @NotNull
    GasOracle gasoracle() throws ApiException;
}
