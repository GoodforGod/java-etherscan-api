package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.model.Price;
import io.goodforgod.api.etherscan.model.Supply;
import java.math.BigInteger;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions <a href="https://etherscan.io/apis#stats">...</a>
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface StatisticAPI {

    /**
     * ERC20 token total Supply
     * 
     * @param contract contract address
     * @return token supply for specified contract
     * @throws EtherScanException parent exception class
     */
    @NotNull
    BigInteger supply(String contract) throws EtherScanException;

    /**
     * Eth total supply
     * 
     * @return total ETH supply for moment
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Supply supply() throws EtherScanException;

    /**
     * Eth last USD and BTC price
     * 
     * @return last usd/btc price for ETH
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Price lastPrice() throws EtherScanException;
}
