package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.executor.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan full API Description <a href="https://etherscan.io/apis">...</a>
 *
 * @author GoodforGod
 * @since 10.05.2023
 */
public interface EtherScanAPI extends AutoCloseable {

    @NotNull
    AccountAPI account();

    @NotNull
    ContractAPI contract();

    @NotNull
    TransactionAPI txs();

    @NotNull
    BlockAPI block();

    @NotNull
    LogsAPI logs();

    @NotNull
    ProxyAPI proxy();

    @NotNull
    StatisticAPI stats();

    @NotNull
    GasTrackerAPI gasTracker();

    @NotNull
    static Builder builder() {
        return new EthScanAPIBuilder();
    }

    interface Builder {

        @NotNull
        Builder withApiKey(@NotNull String apiKey);

        @NotNull
        Builder withNetwork(@NotNull EthNetwork network);

        @NotNull
        Builder withNetwork(@NotNull EthNetworks network);

        @NotNull
        Builder withQueue(@NotNull RequestQueueManager queueManager);

        @NotNull
        Builder withHttpClient(@NotNull Supplier<EthHttpClient> httpClientSupplier);

        @NotNull
        EtherScanAPI build();
    }
}
