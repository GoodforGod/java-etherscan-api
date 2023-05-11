package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.executor.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan full API Description <a href="https://etherscan.io/apis">...</a>
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
final class EtherScanAPIProvider implements EtherScanAPI {

    private final RequestQueueManager requestQueueManager;
    private final AccountAPI account;
    private final BlockAPI block;
    private final ContractAPI contract;
    private final LogsAPI logs;
    private final ProxyAPI proxy;
    private final StatisticAPI stats;
    private final TransactionAPI txs;

    EtherScanAPIProvider(String apiKey,
                         EthNetwork network,
                         Supplier<EthHttpClient> executorSupplier,
                         RequestQueueManager queue) {
        // EtherScan 1request\5sec limit support by queue manager
        final EthHttpClient executor = executorSupplier.get();
        final String baseUrl = network.domain() + "?apikey=" + apiKey;

        this.requestQueueManager = queue;
        this.account = new AccountAPIProvider(queue, baseUrl, executor);
        this.block = new BlockAPIProvider(queue, baseUrl, executor);
        this.contract = new ContractAPIProvider(queue, baseUrl, executor);
        this.logs = new LogsAPIProvider(queue, baseUrl, executor);
        this.proxy = new ProxyAPIProvider(queue, baseUrl, executor);
        this.stats = new StatisticAPIProvider(queue, baseUrl, executor);
        this.txs = new TransactionAPIProvider(queue, baseUrl, executor);
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
    public void close() throws Exception {
        requestQueueManager.close();
    }
}
