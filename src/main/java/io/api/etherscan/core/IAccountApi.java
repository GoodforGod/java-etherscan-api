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

    /** Address ETH balance */
    @NotNull Balance balance(String address) throws ApiException;

    /** ERC20 token balance for address */
    @NotNull TokenBalance balance(String address, String contract) throws ApiException;

    /**
     * Maximum 20 address for single batch request
     * If address > 20, then there will be more than 1 request performed
     */
    @NotNull List<Balance> balances(List<String> addresses) throws ApiException;

    /** All txs */
    @NotNull List<Tx> txs(String address) throws ApiException;
    @NotNull List<Tx> txs(String address, long startBlock) throws ApiException;
    @NotNull List<Tx> txs(String address, long startBlock, long endBlock) throws ApiException;

    /** All internal txs */
    @NotNull List<TxInternal> txsInternal(String address) throws ApiException;
    @NotNull List<TxInternal> txsInternal(String address, long startBlock) throws ApiException;
    @NotNull List<TxInternal> txsInternal(String address, long startBlock, long endBlock) throws ApiException;
    @NotNull List<TxInternal> txsInternalByHash(String txhash);

    /** All token txs */
    @NotNull List<TxToken> txsToken(String address) throws ApiException;
    @NotNull List<TxToken> txsToken(String address, long startBlock) throws ApiException;
    @NotNull List<TxToken> txsToken(String address, long startBlock, long endBlock) throws ApiException;

    /** All blocks mined by address */
    @NotNull List<Block> minedBlocks(String address) throws ApiException;
}
