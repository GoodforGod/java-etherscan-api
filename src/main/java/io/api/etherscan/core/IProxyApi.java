package io.api.etherscan.core;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.model.proxy.BlockProxy;
import io.api.etherscan.model.proxy.ReceiptProxy;
import io.api.etherscan.model.proxy.TxProxy;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Optional;

/**
 * EtherScan - API Descriptions https://etherscan.io/apis#proxy
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface IProxyApi {

    /**
     * Returns the number of most recent block eth_blockNumber
     * 
     * @return last block number
     * @throws ApiException parent exception class
     */
    long blockNoLast();

    /**
     * Returns information about a block by block number eth_getBlockByNumber
     * 
     * @param blockNo block number from 0 to last
     * @return optional block result
     * @throws ApiException parent exception class
     */
    @NotNull
    Optional<BlockProxy> block(long blockNo) throws ApiException;

    /**
     * Returns information about a uncle by block number
     * eth_getUncleByBlockNumberAndIndex
     * 
     * @param blockNo block number from 0 to last
     * @param index   uncle block index
     * @return optional block result
     * @throws ApiException parent exception class
     */
    @NotNull
    Optional<BlockProxy> blockUncle(long blockNo, long index) throws ApiException;

    /**
     * Returns the information about a transaction requested by transaction hash
     * eth_getTransactionByHash
     * 
     * @param txhash transaction hash
     * @return optional tx result
     * @throws ApiException parent exception class
     */
    @NotNull
    Optional<TxProxy> tx(String txhash) throws ApiException;

    /**
     * Returns information about a transaction by block number and transaction index
     * position eth_getTransactionByBlockNumberAndIndex
     * 
     * @param blockNo block number from 0 to last
     * @param index   tx index in block
     * @return optional tx result
     * @throws ApiException parent exception class
     */
    @NotNull
    Optional<TxProxy> tx(long blockNo, long index) throws ApiException;

    /**
     * Returns the number of transactions in a block from a block matching the given
     * block number eth_getBlockTransactionCountByNumber
     * 
     * @param blockNo block number from 0 to last
     * @return transaction amount in block
     * @throws ApiException parent exception class
     */
    int txCount(long blockNo) throws ApiException;

    /**
     * Returns the number of transactions sent from an address
     * eth_getTransactionCount
     * 
     * @param address eth address
     * @return transactions send amount from address
     * @throws ApiException parent exception class
     */
    int txSendCount(String address) throws ApiException;

    /**
     * Creates new message call transaction or a contract creation for signed
     * transactions eth_sendRawTransaction
     * 
     * @param hexEncodedTx encoded hex data to send
     * @return optional string response
     * @throws ApiException parent exception class
     */
    @NotNull
    Optional<String> txSendRaw(String hexEncodedTx) throws ApiException;

    /**
     * Returns the receipt of a transaction by transaction hash
     * eth_getTransactionReceipt
     * 
     * @param txhash transaction hash
     * @return optional tx receipt
     * @throws ApiException parent exception class
     */
    @NotNull
    Optional<ReceiptProxy> txReceipt(String txhash) throws ApiException;

    /**
     * Executes a new message call immediately without creating a transaction on the
     * block chain eth_call
     * 
     * @param address to call
     * @param data    data to call address
     * @return optional the return value of executed contract.
     * @throws ApiException parent exception class
     */
    @NotNull
    Optional<String> call(String address, String data) throws ApiException;

    /**
     * Returns code at a given address eth_getCode
     * 
     * @param address get code from
     * @return optional the code from the given address
     * @throws ApiException parent exception class
     */
    @NotNull
    Optional<String> code(String address) throws ApiException;

    /**
     * (**experimental) Returns the value from a storage position at a given address
     * eth_getStorageAt
     * 
     * @param address  to get storage
     * @param position storage position
     * @return optional the value at this storage position
     * @throws ApiException parent exception class
     */
    @NotNull
    Optional<String> storageAt(String address, long position) throws ApiException;

    /**
     * Returns the current price per gas in wei eth_gasPrice
     * 
     * @return estimated gas price
     * @throws ApiException parent exception class
     */
    @NotNull
    BigInteger gasPrice() throws ApiException;

    /**
     * Makes a call or transaction, which won't be added to the blockchain and
     * returns the used gas, which can be used for estimating the used gas
     * eth_estimateGas
     * 
     * @param hexData data to calc gas usage for
     * @return estimated gas usage
     * @throws ApiException parent exception class
     */
    @NotNull
    BigInteger gasEstimated(String hexData) throws ApiException;

    @NotNull
    BigInteger gasEstimated() throws ApiException;
}
