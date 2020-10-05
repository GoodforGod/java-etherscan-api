package io.api.etherscan.core.impl;

import io.api.etherscan.core.*;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.error.ApiKeyException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.executor.impl.HttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.manager.impl.FakeQueueManager;
import io.api.etherscan.manager.impl.QueueManager;
import io.api.etherscan.model.EthNetwork;
import io.api.etherscan.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

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

    public EtherScanApi() {
        this("YourApiKeyToken", EthNetwork.MAINNET);
    }

    public EtherScanApi(final EthNetwork network) {
        this("YourApiKeyToken", network);
    }

    public EtherScanApi(final String apiKey) {
        this(apiKey, EthNetwork.MAINNET);
    }

    public EtherScanApi(final EthNetwork network,
                        final Supplier<IHttpExecutor> executorSupplier) {
        this("YourApiKeyToken", network, executorSupplier);
    }

    public EtherScanApi(final String apiKey,
                        final EthNetwork network) {
        this(apiKey, network, DEFAULT_SUPPLIER);
    }

    public EtherScanApi(final String apiKey,
                        final EthNetwork network,
                        final Supplier<IHttpExecutor> executorSupplier) {
        if (BasicUtils.isBlank(apiKey))
            throw new ApiKeyException("API key can not be null or empty");

        if(network == null)
            throw new ApiException("Ethereum Network is set to NULL value");

        // EtherScan 5request\sec limit support by queue manager
        final IQueueManager masterQueue = (apiKey.equals("YourApiKeyToken"))
                ? new FakeQueueManager()
                : new QueueManager(5, 1);

        final IHttpExecutor executor = executorSupplier.get();

        final String ending = (EthNetwork.TOBALABA.equals(network)) ? "com" : "io";
        final String baseUrl = "https://" + network.getDomain() + ".etherscan." + ending + "/api" + "?apikey=" + apiKey;

        this.account = new AccountApiProvider(masterQueue, baseUrl, executor);
        this.block = new BlockApiProvider(masterQueue, baseUrl, executor);
        this.contract = new ContractApiProvider(masterQueue, baseUrl, executor);
        this.logs = new LogsApiProvider(masterQueue, baseUrl, executor);
        this.proxy = new ProxyApiProvider(masterQueue, baseUrl, executor);
        this.stats = new StatisticApiProvider(masterQueue, baseUrl, executor);
        this.txs = new TransactionApiProvider(masterQueue, baseUrl, executor);
    }

    @NotNull
    public IAccountApi account() {
        return account;
    }

    @NotNull
    public IContractApi contract() {
        return contract;
    }

    @NotNull
    public ITransactionApi txs() {
        return txs;
    }

    @NotNull
    public IBlockApi block() {
        return block;
    }

    @NotNull
    public ILogsApi logs() {
        return logs;
    }

    @NotNull
    public IProxyApi proxy() {
        return proxy;
    }

    @NotNull
    public IStatisticApi stats() {
        return stats;
    }
}
