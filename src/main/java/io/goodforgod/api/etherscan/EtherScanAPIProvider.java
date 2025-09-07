package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan full API Description <a href="https://etherscan.io/apis">...</a>
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class EtherScanAPIProvider implements EtherScanAPI {

    private final RequestQueueManager requestQueueManager;
    private final AccountAPI account;
    private final BlockAPI block;
    private final ContractAPI contract;
    private final LogsAPI logs;
    private final ProxyAPI proxy;
    private final StatisticAPI stats;
    private final TransactionAPI txs;
    private final GasTrackerAPI gasTracker;

    public EtherScanAPIProvider(String apiKey,
                                EthNetwork network,
                                RequestQueueManager queue,
                                EthHttpClient ethHttpClient,
                                Converter converter,
                                int retryCount) {
        // EtherScan 1request\5sec limit support by queue manager
        final String baseUrl = network.domain() + "?apikey=" + apiKey;

        this.requestQueueManager = queue;
        this.account = new AccountAPIProvider(queue, baseUrl, ethHttpClient, converter, retryCount);
        this.block = new BlockAPIProvider(queue, baseUrl, ethHttpClient, converter, retryCount);
        this.contract = new ContractAPIProvider(queue, baseUrl, ethHttpClient, converter, retryCount);
        this.logs = new LogsAPIProvider(queue, baseUrl, ethHttpClient, converter, retryCount);
        this.proxy = new ProxyAPIProvider(queue, baseUrl, ethHttpClient, converter, retryCount);
        this.stats = new StatisticAPIProvider(queue, baseUrl, ethHttpClient, converter, retryCount);
        this.txs = new TransactionAPIProvider(queue, baseUrl, ethHttpClient, converter, retryCount);
        this.gasTracker = new GasTrackerAPIProvider(queue, baseUrl, ethHttpClient, converter, retryCount);
    }

    @NotNull
    @Override
    public AccountAPI account() {
        return account;
    }

    @NotNull
    @Override
    public ContractAPI contract() {
        return contract;
    }

    @NotNull
    @Override
    public TransactionAPI txs() {
        return txs;
    }

    @NotNull
    @Override
    public BlockAPI block() {
        return block;
    }

    @NotNull
    @Override
    public LogsAPI logs() {
        return logs;
    }

    @NotNull
    @Override
    public ProxyAPI proxy() {
        return proxy;
    }

    @NotNull
    @Override
    public StatisticAPI stats() {
        return stats;
    }

    @Override
    public @NotNull GasTrackerAPI gasTracker() {
        return gasTracker;
    }

    @Override
    public void close() throws Exception {
        requestQueueManager.close();
    }
}
