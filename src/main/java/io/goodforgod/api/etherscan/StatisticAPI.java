package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.model.EthSupply;
import io.goodforgod.api.etherscan.model.Price;
import io.goodforgod.api.etherscan.model.Wei;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions <a href="https://docs.etherscan.io/api-endpoints/stats-1">...</a>
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface StatisticAPI {

    /**
     * ERC20 token total Supply
     * <a href=
     * "https://docs.etherscan.io/api-endpoints/tokens#get-erc20-token-totalsupply-by-contractaddress">EtherScan<a>
     *
     * @param contract contract address
     * @return token supply for specified contract
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Wei supply(String contract) throws EtherScanException;

    /**
     * Returns the current amount of Ether in circulation excluding ETH2 Staking rewards and EIP1559
     * burnt fees.
     * 
     * @return total ETH supply for moment
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Wei supply() throws EtherScanException;

    /**
     * Returns the current amount of Ether in circulation, ETH2 Staking rewards, EIP1559 burnt fees, and
     * total withdrawn ETH from the beacon chain.
     *
     * @return total ETH supply for moment
     * @throws EtherScanException parent exception class
     */
    @NotNull
    EthSupply supplyTotal() throws EtherScanException;

    /**
     * Eth last USD and BTC price
     * 
     * @return last usd/btc price for ETH
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Price priceLast() throws EtherScanException;
}
