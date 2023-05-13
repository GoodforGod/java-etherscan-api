package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.model.GasOracle;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions
 * <a href="https://docs.etherscan.io/api-endpoints/gas-tracker">...</a>
 *
 * @author Abhay Gupta
 * @since 14.11.2022
 */
public interface GasTrackerAPI {

    /**
     * GasOracle details
     *
     * @return fast, suggested gas price
     * @throws EtherScanException parent exception class
     */
    @NotNull
    GasOracle oracle() throws EtherScanException;
}
