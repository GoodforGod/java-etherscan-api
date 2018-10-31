package io.api.core.impl;

import io.api.core.IAccountProvider;
import io.api.core.IBlockProvider;
import io.api.core.IContractProvider;
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

    private final IContractProvider contract;
    private final IAccountProvider account;
    private final IBlockProvider block;

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

        this.contract = new ContractProvider(masterQueue, baseUrl, executor);
        this.account = new AccountProvider(masterQueue, baseUrl, executor);
        this.block = new BlockProvider(masterQueue, baseUrl, executor);
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
}
