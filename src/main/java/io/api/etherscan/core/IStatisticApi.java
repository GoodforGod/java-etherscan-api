package io.api.etherscan.core;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.model.Price;
import io.api.etherscan.model.Supply;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#stats
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface IStatisticApi {

    /**
     * ERC20 token total Supply
     * @param contract to look for
     * @return token supply
     */
    @NotNull BigInteger supply(String contract) throws ApiException;

    /**
     * Eth total supply
     * @return ETH supply
     */
    @NotNull Supply supply() throws ApiException;

    /**
     * Eth last USD and BTC price
     * @return last price
     */
    @NotNull Price lastPrice() throws ApiException;
}
