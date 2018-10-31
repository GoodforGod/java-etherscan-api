package io.api.core.impl;

import io.api.core.*;
import io.api.executor.IHttpExecutor;
import io.api.executor.impl.HttpExecutor;
import io.api.manager.IQueueManager;
import io.api.manager.impl.QueueManager;
import io.api.model.EthereumNetwork;

import java.util.function.Supplier;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class EtherScanApi {

    private static final Supplier<IHttpExecutor> DEFAULT_SUPPLIER = HttpExecutor::new;

    private final IAccountProvider account;
    private final IBlockProvider block;
    private final IContractProvider contract;
    private final ILogsProvider logs;
    //private final IParityProvider parityProvider;
    private final IStatisticProvider stats;
    private final ITransactionProvider txs;

    public EtherScanApi(final String apiKey) {
        this(apiKey, EthereumNetwork.MAINNET);
    }

    public EtherScanApi(final String apiKey,
                        final EthereumNetwork network) {
        this(apiKey, network, DEFAULT_SUPPLIER);
    }

    public EtherScanApi(final String apiKey,
                        final EthereumNetwork network,
                        final Supplier<IHttpExecutor> executorSupplier) {
        // EtherScan 5request\sec limit support by queue manager
        final IQueueManager masterQueue = new QueueManager(5, 1);
        final IHttpExecutor executor = executorSupplier.get();

        final EthereumNetwork usedNetwork = (network == null) ? EthereumNetwork.MAINNET : network;
        final String baseUrl = "https://" + usedNetwork.getDomain() + ".etherscan.io/api" + "?apikey=" + apiKey;

        this.account = new AccountProvider(masterQueue, baseUrl, executor);
        this.block  = new BlockProvider(masterQueue, baseUrl, executor);
        this.contract = new ContractProvider(masterQueue, baseUrl, executor);
        this.logs   = new LogsProvider(masterQueue, baseUrl, executor);
        this.stats  = new StatisticProvider(masterQueue, baseUrl, executor);
        this.txs    = new TransactionProvider(masterQueue, baseUrl, executor);
    }

    public IContractProvider contract() {
        return contract;
    }

    public IAccountProvider account() {
        return account;
    }

    public IBlockProvider block() {
        return block;
    }

    public ILogsProvider logs() {
        return logs;
    }

    public IStatisticProvider stats() {
        return stats;
    }

    public ITransactionProvider txs() {
        return txs;
    }
}
