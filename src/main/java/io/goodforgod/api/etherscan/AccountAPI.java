package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.model.*;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions <a href="https://docs.etherscan.io/api-endpoints/accounts">...</a>
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public interface AccountAPI {

    /**
     * Address ETH balance
     * 
     * @param address get balance for
     * @return balance
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Balance balance(String address) throws EtherScanException;

    /**
     * ERC20 token balance for address
     * 
     * @param address  get balance for
     * @param contract token contract
     * @return token balance for address
     * @throws EtherScanException parent exception class
     */
    @NotNull
    TokenBalance balance(String address, String contract) throws EtherScanException;

    /**
     * Maximum 20 address for single batch request If address MORE THAN 20, then there will be more than
     * 1 request performed
     * 
     * @param addresses addresses to get balances for
     * @return list of balances
     * @throws EtherScanException parent exception class
     */
    @NotNull
    List<Balance> balances(List<String> addresses) throws EtherScanException;

    /**
     * All txs for given address
     * 
     * @param address    get txs for
     * @param startBlock tx from this blockNumber
     * @param endBlock   tx to this blockNumber
     * @return txs for address
     * @throws EtherScanException parent exception class
     */
    @NotNull
    List<Tx> txs(String address, long startBlock, long endBlock) throws EtherScanException;

    @NotNull
    List<Tx> txs(String address, long startBlock) throws EtherScanException;

    @NotNull
    List<Tx> txs(String address) throws EtherScanException;

    /**
     * All internal txs for given address
     * 
     * @param address    get txs for
     * @param startBlock tx from this blockNumber
     * @param endBlock   tx to this blockNumber
     * @return txs for address
     * @throws EtherScanException parent exception class
     */
    @NotNull
    List<TxInternal> txsInternal(String address, long startBlock, long endBlock) throws EtherScanException;

    @NotNull
    List<TxInternal> txsInternal(String address, long startBlock) throws EtherScanException;

    @NotNull
    List<TxInternal> txsInternal(String address) throws EtherScanException;

    /**
     * All internal tx for given transaction hash
     * 
     * @param txhash transaction hash
     * @return internal txs list
     * @throws EtherScanException parent exception class
     */
    @NotNull
    List<TxInternal> txsInternalByHash(String txhash) throws EtherScanException;

    /**
     * All ERC-20 token txs for given address
     * 
     * @param address    get txs for
     * @param startBlock tx from this blockNumber
     * @param endBlock   tx to this blockNumber
     * @return txs for address
     * @throws EtherScanException parent exception class
     */
    @NotNull
    List<TxErc20> txsErc20(String address, long startBlock, long endBlock) throws EtherScanException;

    @NotNull
    List<TxErc20> txsErc20(String address, long startBlock) throws EtherScanException;

    @NotNull
    List<TxErc20> txsErc20(String address) throws EtherScanException;

    /**
     * All ERC-20 token txs for given address and contract address
     *
     * @param address         get txs for
     * @param contractAddress contract address to get txs for
     * @param startBlock      tx from this blockNumber
     * @param endBlock        tx to this blockNumber
     * @return txs for address
     * @throws EtherScanException parent exception class
     */
    @NotNull
    List<TxErc20> txsErc20(String address, String contractAddress, long startBlock, long endBlock) throws EtherScanException;

    @NotNull
    List<TxErc20> txsErc20(String address, String contractAddress, long startBlock) throws EtherScanException;

    @NotNull
    List<TxErc20> txsErc20(String address, String contractAddress) throws EtherScanException;

    /**
     * All ERC-721 (NFT) token txs for given address
     *
     * @param address    get txs for
     * @param startBlock tx from this blockNumber
     * @param endBlock   tx to this blockNumber
     * @return txs for address
     * @throws EtherScanException parent exception class
     */
    @NotNull
    List<TxErc721> txsErc721(String address, long startBlock, long endBlock) throws EtherScanException;

    @NotNull
    List<TxErc721> txsErc721(String address, long startBlock) throws EtherScanException;

    @NotNull
    List<TxErc721> txsErc721(String address) throws EtherScanException;

    /**
     * All ERC-721 (NFT) token txs for given address
     *
     * @param address    get txs for
     * @param startBlock tx from this blockNumber
     * @param endBlock   tx to this blockNumber
     * @return txs for address
     * @throws EtherScanException parent exception class
     */
    @NotNull
    List<TxErc721> txsErc721(String address, String contractAddress, long startBlock, long endBlock) throws EtherScanException;

    @NotNull
    List<TxErc721> txsErc721(String address, String contractAddress, long startBlock) throws EtherScanException;

    @NotNull
    List<TxErc721> txsErc721(String address, String contractAddress) throws EtherScanException;

    /**
     * All ERC-721 (NFT) token txs for given address
     *
     * @param address    get txs for
     * @param startBlock tx from this blockNumber
     * @param endBlock   tx to this blockNumber
     * @return txs for address
     * @throws EtherScanException parent exception class
     */
    @NotNull
    List<TxErc1155> txsErc1155(String address, long startBlock, long endBlock) throws EtherScanException;

    @NotNull
    List<TxErc1155> txsErc1155(String address, long startBlock) throws EtherScanException;

    @NotNull
    List<TxErc1155> txsErc1155(String address) throws EtherScanException;

    /**
     * All ERC-721 (NFT) token txs for given address
     *
     * @param address    get txs for
     * @param startBlock tx from this blockNumber
     * @param endBlock   tx to this blockNumber
     * @return txs for address
     * @throws EtherScanException parent exception class
     */
    @NotNull
    List<TxErc1155> txsErc1155(String address, String contractAddress, long startBlock, long endBlock) throws EtherScanException;

    @NotNull
    List<TxErc1155> txsErc1155(String address, String contractAddress, long startBlock) throws EtherScanException;

    @NotNull
    List<TxErc1155> txsErc1155(String address, String contractAddress) throws EtherScanException;

    /**
     * All blocks mined by address
     * 
     * @param address address to search for
     * @return blocks mined
     * @throws EtherScanException parent exception class
     */
    @NotNull
    List<Block> blocksMined(String address) throws EtherScanException;
}
