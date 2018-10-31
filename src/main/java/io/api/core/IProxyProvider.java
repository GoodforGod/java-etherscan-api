package io.api.core;

import io.api.model.proxy.BlockProxy;
import io.api.model.proxy.TxProxy;
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
public interface IProxyProvider {

    long blockNoLast();

    @NotNull Optional<BlockProxy> block(long blockNo);
    @NotNull Optional<BlockProxy> blockUncle(long blockNo, long index);

    @NotNull Optional<TxProxy> tx(String txhash);
    @NotNull Optional<TxProxy> tx(long blockNo, long index);

    int txCount(long blockNo);
    int txSendCount(String address);
    boolean txSendRaw(String hexEncodedTx);

    @NotNull String call(String address, String data);

    @NotNull String code(String address);

    @NotNull String storageAt(String address, long position);

    @NotNull BigInteger gasPrice();
    @NotNull BigInteger gasEstimated();
}
