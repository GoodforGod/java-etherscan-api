package io.api.etherscan.core;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.model.proxy.BlockProxy;
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

    /** Returns the number of most recent block */
    long blockNoLast();

    /** Returns information about a block by block number */
    @NotNull Optional<BlockProxy> block(long blockNo) throws ApiException;

    /** Returns information about a uncle by block number */
    @NotNull Optional<BlockProxy> blockUncle(long blockNo, long index) throws ApiException;

    /** Returns the information about a transaction requested by transaction hash */
    @NotNull Optional<TxProxy> tx(String txhash) throws ApiException;

    /** Returns information about a transaction by block number and transaction index position */
    @NotNull Optional<TxProxy> tx(long blockNo, long index) throws ApiException;

    /** Returns the number of transactions in a block from a block matching the given block number */
    int txCount(long blockNo) throws ApiException;

    /** Returns the number of transactions sent from an address */
    int txSendCount(String address) throws ApiException;

    //TODO implement
    /** Creates new message call transaction or a contract creation for signed transactions */
    boolean txSendRaw(String hexEncodedTx) throws ApiException;

    /** Executes a new message call immediately without creating a transaction on the block chain */
    @NotNull String call(String address, String data) throws ApiException;

    /** Returns code at a given address */
    @NotNull String code(String address) throws ApiException;

    /**
     * (**experimental)
     * Returns the value from a storage position at a given address
     */
    @NotNull String storageAt(String address, long position) throws ApiException;

    /** Returns the current price per gas in wei */
    @NotNull BigInteger gasPrice() throws ApiException;

    /**
     * Makes a call or transaction, which won't be added to the blockchain and returns the used gas,
     * which can be used for estimating the used gas
     */
    @NotNull BigInteger gasEstimated() throws ApiException;
    @NotNull BigInteger gasEstimated(String hexData) throws ApiException;
}
