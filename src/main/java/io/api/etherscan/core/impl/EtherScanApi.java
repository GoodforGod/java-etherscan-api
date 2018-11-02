package io.api.etherscan.core.impl;

import io.api.etherscan.core.*;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.executor.impl.HttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.manager.impl.QueueManager;
import io.api.etherscan.model.EthNetwork;

import java.util.function.Supplier;

/**
 * EtherScan full API Description
 * https://etherscan.io/apis
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class EtherScanApi {

    private static final Supplier<IHttpExecutor> DEFAULT_SUPPLIER = HttpExecutor::new;

    private final IAccountApi account;
    private final IBlockApi block;
    private final IContractApi contract;
    private final ILogsApi logs;
    private final IProxyApi proxy;
    private final IStatisticApi stats;
    private final ITransactionApi txs;

    public EtherScanApi(final String apiKey) {
        this(apiKey, EthNetwork.MAINNET);
    }

    public EtherScanApi(final String apiKey,
                        final EthNetwork network) {
        this(apiKey, network, DEFAULT_SUPPLIER);
    }

    public EtherScanApi(final String apiKey,
                        final EthNetwork network,
                        final Supplier<IHttpExecutor> executorSupplier) {
        // EtherScan 5request\sec limit support by queue manager
        final IQueueManager masterQueue = new QueueManager(5, 1);
        final IHttpExecutor executor = executorSupplier.get();

        final EthNetwork usedNetwork = (network == null) ? EthNetwork.MAINNET : network;
        final String baseUrl = "https://" + usedNetwork.getDomain() + ".etherscan.io/api" + "?apikey=" + apiKey;

        this.account = new AccountApiProvider(masterQueue, baseUrl, executor);
        this.block  = new BlockApiProvider(masterQueue, baseUrl, executor);
        this.contract = new ContractApiProvider(masterQueue, baseUrl, executor);
        this.logs   = new LogsApiProvider(masterQueue, baseUrl, executor);
        this.proxy  = new ProxyApiProvider(masterQueue, baseUrl, executor);
        this.stats  = new StatisticApiProvider(masterQueue, baseUrl, executor);
        this.txs    = new TransactionApiProvider(masterQueue, baseUrl, executor);
    }

    public IContractApi contract() {
        return contract;
    }

    public IAccountApi account() {
        return account;
    }

    public IBlockApi block() {
        return block;
    }

    public ILogsApi logs() {
        return logs;
    }

    public IStatisticApi stats() {
        return stats;
    }

    public ITransactionApi txs() {
        return txs;
    }

    public IProxyApi proxy() {
        return proxy;
    }
}
