package io.api.etherscan.core;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#accounts
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public interface IAccountApi {

    /**
     * Address ETH balance
     * @param address to look for
     * @return balance
     */
    @NotNull Balance balance(String address) throws ApiException;

    /**
     * ERC20 token balance for address
     * @param address to look for
     * @param contract for token
     * @return  token balance
     */
    @NotNull TokenBalance balance(String address, String contract) throws ApiException;

    /**
     * Maximum 20 address for single batch request
     * If addresses more than 20, then there will be MORE than 1 request performed
     * @param addresses to look for
     * @return balance[0] for address[0], etc
     */
    @NotNull List<Balance> balances(List<String> addresses) throws ApiException;

    /**
     * Find all txs
     * @param address to look for txs
     * @return tx info
     */
    @NotNull List<Tx> txs(String address) throws ApiException;
    @NotNull List<Tx> txs(String address, long startBlock) throws ApiException;
    @NotNull List<Tx> txs(String address, long startBlock, long endBlock) throws ApiException;

    /**
     * All internal txs
     * @param address to look for
     * @return internal tx
     */
    @NotNull List<TxInternal> txsInternal(String address) throws ApiException;
    @NotNull List<TxInternal> txsInternal(String address, long startBlock) throws ApiException;
    @NotNull List<TxInternal> txsInternal(String address, long startBlock, long endBlock) throws ApiException;
    @NotNull List<TxInternal> txsInternalByHash(String txhash);

    /**
     * All token txs
     * @param address to look for
     * @return token txs
     */
    @NotNull List<TxToken> txsToken(String address) throws ApiException;
    @NotNull List<TxToken> txsToken(String address, long startBlock) throws ApiException;
    @NotNull List<TxToken> txsToken(String address, long startBlock, long endBlock) throws ApiException;

    /**
     * All blocks mined by address
     * @param address to look for
     * @return mined blocks
     */
    @NotNull List<Block> minedBlocks(String address) throws ApiException;
}
