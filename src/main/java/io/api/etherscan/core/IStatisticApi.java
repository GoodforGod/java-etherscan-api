package io.api.etherscan.core;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.model.Price;
import io.api.etherscan.model.Supply;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * EtherScan - API Descriptions https://etherscan.io/apis#stats
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface IStatisticApi {

    /**
     * ERC20 token total Supply
     * 
     * @param contract contract address
     * @return token supply for specified contract
     * @throws ApiException parent exception class
     */
    @NotNull
    BigInteger supply(String contract) throws ApiException;

    /**
     * Eth total supply
     * 
     * @return total ETH supply for moment
     * @throws ApiException parent exception class
     */
    @NotNull
    Supply supply() throws ApiException;

    /**
     * Eth last USD and BTC price
     * 
     * @return last usd/btc price for ETH
     * @throws ApiException parent exception class
     */
    @NotNull
    Price lastPrice() throws ApiException;
}
