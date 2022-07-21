package io.api.etherscan.core;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * EtherScan - API Descriptions https://etherscan.io/apis#accounts
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public interface IAccountApi {

    /**
     * Address ETH balance
     * 
     * @param address get balance for
     * @return balance
     * @throws ApiException parent exception class
     */
    @NotNull
    Balance balance(String address) throws ApiException;

    /**
     * ERC20 token balance for address
     * 
     * @param address  get balance for
     * @param contract token contract
     * @return token balance for address
     * @throws ApiException parent exception class
     */
    @NotNull
    TokenBalance balance(String address, String contract) throws ApiException;

    /**
     * Maximum 20 address for single batch request If address MORE THAN 20, then there will be more than
     * 1 request performed
     * 
     * @param addresses addresses to get balances for
     * @return list of balances
     * @throws ApiException parent exception class
     */
    @NotNull
    List<Balance> balances(List<String> addresses) throws ApiException;

    /**
     * All txs for given address
     * 
     * @param address    get txs for
     * @param startBlock tx from this blockNumber
     * @param endBlock   tx to this blockNumber
     * @return txs for address
     * @throws ApiException parent exception class
     */
    @NotNull
    List<Tx> txs(String address, long startBlock, long endBlock) throws ApiException;

    @NotNull
    List<Tx> txs(String address, long startBlock) throws ApiException;

    @NotNull
    List<Tx> txs(String address) throws ApiException;

    /**
     * All internal txs for given address
     * 
     * @param address    get txs for
     * @param startBlock tx from this blockNumber
     * @param endBlock   tx to this blockNumber
     * @return txs for address
     * @throws ApiException parent exception class
     */
    @NotNull
    List<TxInternal> txsInternal(String address, long startBlock, long endBlock) throws ApiException;

    @NotNull
    List<TxInternal> txsInternal(String address, long startBlock) throws ApiException;

    @NotNull
    List<TxInternal> txsInternal(String address) throws ApiException;

    /**
     * All internal tx for given transaction hash
     * 
     * @param txhash transaction hash
     * @return internal txs list
     * @throws ApiException parent exception class
     */
    @NotNull
    List<TxInternal> txsInternalByHash(String txhash) throws ApiException;

    /**
     * All ERC-20 token txs for given address
     * 
     * @param address    get txs for
     * @param startBlock tx from this blockNumber
     * @param endBlock   tx to this blockNumber
     * @return txs for address
     * @throws ApiException parent exception class
     */
    @NotNull
    List<TxToken> txsToken(String address, long startBlock, long endBlock) throws ApiException;

    @NotNull
    List<TxToken> txsToken(String address, long startBlock) throws ApiException;

    @NotNull
    List<TxToken> txsToken(String address) throws ApiException;

    /**
     * All ERC-20 token txs for given address and contract address
     *
     * @param address    get txs for
     * @param contractAddress contract address to get txs for
     * @param startBlock tx from this blockNumber
     * @param endBlock   tx to this blockNumber
     * @return txs for address
     * @throws ApiException parent exception class
     */
    @NotNull
    List<TxToken> txsToken(String address, String contractAddress, long startBlock, long endBlock) throws ApiException;

    @NotNull
    List<TxToken> txsToken(String address, String contractAddress, long startBlock) throws ApiException;

    @NotNull
    List<TxToken> txsToken(String address, String contractAddress) throws ApiException;

    /**
     * All ERC-721 (NFT) token txs for given address
     *
     * @param address    get txs for
     * @param startBlock tx from this blockNumber
     * @param endBlock   tx to this blockNumber
     * @return txs for address
     * @throws ApiException parent exception class
     */
    @NotNull
    List<TxToken> txsNftToken(String address, long startBlock, long endBlock) throws ApiException;

    @NotNull
    List<TxToken> txsNftToken(String address, long startBlock) throws ApiException;

    @NotNull
    List<TxToken> txsNftToken(String address) throws ApiException;

    /**
     * All blocks mined by address
     * 
     * @param address address to search for
     * @return blocks mined
     * @throws ApiException parent exception class
     */
    @NotNull
    List<Block> minedBlocks(String address) throws ApiException;
}
