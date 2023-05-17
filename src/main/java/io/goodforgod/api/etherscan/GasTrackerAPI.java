package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.model.GasOracle;
import io.goodforgod.api.etherscan.model.Wei;
import java.time.Duration;
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
     * Returns the estimated time for a transaction to be confirmed on the blockchain.
     *
     * @return estimated time
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Duration estimate(@NotNull Wei wei) throws EtherScanException;

    /**
     * Returns the current Safe, Proposed and Fast gas prices.
     *
     * @return fast, suggested gas price
     * @throws EtherScanException parent exception class
     */
    @NotNull
    GasOracle oracle() throws EtherScanException;
}
