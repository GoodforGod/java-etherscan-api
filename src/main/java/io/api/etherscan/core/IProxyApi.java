package io.api.etherscan.core;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.model.proxy.BlockProxy;
import io.api.etherscan.model.proxy.ReceiptProxy;
import io.api.etherscan.model.proxy.TxProxy;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Optional;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#proxy
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface IProxyApi {

    /**
     * Returns the number of most recent block
     * eth_blockNumber
     * @return last block number
     */
    long blockNoLast();

    /**
     * Returns information about a block by block number
     * eth_getBlockByNumber
     * @param blockNo block number
     * @return block info
     */
    @NotNull Optional<BlockProxy> block(long blockNo) throws ApiException;

    /**
     * Returns information about a uncle by block number
     * eth_getUncleByBlockNumberAndIndex
     * @param blockNo block number
     * @param index uncle block index
     * @return block info
     */
    @NotNull Optional<BlockProxy> blockUncle(long blockNo, long index) throws ApiException;

    /**
     * Returns the information about a transaction requested by transaction hash
     * eth_getTransactionByHash
     * @param txhash tx hash
     * @return tx info
     */
    @NotNull Optional<TxProxy> tx(String txhash) throws ApiException;

    /**
     * Returns information about a transaction by block number and transaction index position
     * eth_getTransactionByBlockNumberAndIndex
     * @param blockNo block number
     * @param index tx index in block
     * @return tx info
     */
    @NotNull Optional<TxProxy> tx(long blockNo, long index) throws ApiException;

    /**
     * Returns the number of transactions in a block from a block matching the given block number
     * eth_getBlockTransactionCountByNumber
     * @param blockNo block number
     * @return tx count in block
     */
    int txCount(long blockNo) throws ApiException;

    /**
     * Returns the number of transactions sent from an address
     * eth_getTransactionCount
     * @param address to look for
     * @return tx send count
     */
    int txSendCount(String address) throws ApiException;

    /**
     * Creates new message call transaction or a contract creation for signed transactions
     * eth_sendRawTransaction
     * @param hexEncodedTx tx as hex
     * @return result (check eth grpc info)
     */
    @NotNull Optional<String> txSendRaw(String hexEncodedTx) throws ApiException;

    /**
     * Returns the receipt of a transaction by transaction hash
     * eth_getTransactionReceipt
     * @param txhash tx hash
     * @return receipt
     */
    @NotNull Optional<ReceiptProxy> txReceipt(String txhash) throws ApiException;

    /**
     * Executes a new message call immediately without creating a transaction on the block chain
     * eth_call
     * @param address to look for
     * @param data in tx for call
     * @return result (check eth grpc info)
     */
    @NotNull Optional<String> call(String address, String data) throws ApiException;

    /**
     * Returns code at a given address
     * eth_getCode
     * @param address to look for
     * @return result (check eth grpc info)
     */
    @NotNull Optional<String> code(String address) throws ApiException;

    /**
     * (**experimental)
     * Returns the value from a storage position at a given address
     * eth_getStorageAt
     * @param address to look for
     * @param position storage position
     * @return result (check eth grpc info)
     */
    @NotNull Optional<String> storageAt(String address, long position) throws ApiException;

    /**
     * Returns the current price per gas in wei
     * eth_gasPrice
     * @return price
     */
    @NotNull BigInteger gasPrice() throws ApiException;

    /**
     * Makes a call or transaction, which won't be added to the blockchain and returns the used gas,
     * which can be used for estimating the used gas
     * eth_estimateGas
     * @return gas estimate
     */
    @NotNull BigInteger gasEstimated() throws ApiException;
    @NotNull BigInteger gasEstimated(String hexData) throws ApiException;
}
