package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.model.Wei;
import io.goodforgod.api.etherscan.model.proxy.BlockProxy;
import io.goodforgod.api.etherscan.model.proxy.ReceiptProxy;
import io.goodforgod.api.etherscan.model.proxy.TxProxy;
import java.util.Optional;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions
 * <a href="https://docs.etherscan.io/api-endpoints/geth-parity-proxy">...</a>
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface ProxyAPI {

    /**
     * Returns the number of most recent block eth_blockNumber
     * 
     * @return last block number
     * @throws EtherScanException parent exception class
     */
    long blockNoLast();

    /**
     * Returns information about a block by block number eth_getBlockByNumber
     * 
     * @param blockNo block number from 0 to last
     * @return optional block result
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Optional<BlockProxy> block(long blockNo) throws EtherScanException;

    /**
     * Returns information about a uncle by block number eth_getUncleByBlockNumberAndIndex
     * 
     * @param blockNo block number from 0 to last
     * @param index   uncle block index
     * @return optional block result
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Optional<BlockProxy> blockUncle(long blockNo, long index) throws EtherScanException;

    /**
     * Returns the information about a transaction requested by transaction hash
     * eth_getTransactionByHash
     * 
     * @param txhash transaction hash
     * @return optional tx result
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Optional<TxProxy> tx(@NotNull String txhash) throws EtherScanException;

    /**
     * Returns information about a transaction by block number and transaction index position
     * eth_getTransactionByBlockNumberAndIndex
     * 
     * @param blockNo block number from 0 to last
     * @param index   tx index in block
     * @return optional tx result
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Optional<TxProxy> tx(long blockNo, long index) throws EtherScanException;

    /**
     * Returns the number of transactions in a block from a block matching the given block number
     * eth_getBlockTransactionCountByNumber
     * 
     * @param blockNo block number from 0 to last
     * @return transaction amount in block
     * @throws EtherScanException parent exception class
     */
    int txCount(long blockNo) throws EtherScanException;

    /**
     * Returns the number of transactions sent from an address eth_getTransactionCount
     * 
     * @param address eth address
     * @return transactions send amount from address
     * @throws EtherScanException parent exception class
     */
    int txSendCount(@NotNull String address) throws EtherScanException;

    /**
     * Creates new message call transaction or a contract creation for signed transactions
     * eth_sendRawTransaction
     * 
     * @param hexEncodedTx encoded hex data to send
     * @return optional string response
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Optional<String> txSendRaw(@NotNull String hexEncodedTx) throws EtherScanException;

    /**
     * Returns the receipt of a transaction by transaction hash eth_getTransactionReceipt
     * 
     * @param txhash transaction hash
     * @return optional tx receipt
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Optional<ReceiptProxy> txReceipt(@NotNull String txhash) throws EtherScanException;

    /**
     * Executes a new message call immediately without creating a transaction on the block chain
     * eth_call
     * 
     * @param address to call
     * @param data    data to call address
     * @return optional the return value of executed contract.
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Optional<String> call(@NotNull String address, @NotNull String data) throws EtherScanException;

    /**
     * Returns code at a given address eth_getCode
     * 
     * @param address get code from
     * @return optional the code from the given address
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Optional<String> code(@NotNull String address) throws EtherScanException;

    /**
     * (**experimental) Returns the value from a storage position at a given address eth_getStorageAt
     * 
     * @param address  to get storage
     * @param position storage position
     * @return optional the value at this storage position
     * @throws EtherScanException parent exception class
     */
    @Experimental
    @NotNull
    Optional<String> storageAt(@NotNull String address, long position) throws EtherScanException;

    /**
     * Returns the current price per gas in wei eth_gasPrice
     * 
     * @return estimated gas price
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Wei gasPrice() throws EtherScanException;

    /**
     * Makes a call or transaction, which won't be added to the blockchain and returns the used gas,
     * which can be used for estimating the used gas eth_estimateGas
     * 
     * @param hexData data to calc gas usage for
     * @return estimated gas usage
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Wei gasEstimated(@NotNull String hexData) throws EtherScanException;

    @NotNull
    Wei gasEstimated() throws EtherScanException;
}
