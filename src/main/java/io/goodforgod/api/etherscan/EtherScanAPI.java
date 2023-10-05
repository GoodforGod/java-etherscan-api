package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanRateLimitException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

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
        Builder withConverter(@NotNull Supplier<Converter> converterSupplier);

        /**
         * By default is disabled
         *
         * @param maxRetryCount to retry if {@link EtherScanRateLimitException} thrown
         * @return self
         */
        @NotNull
        EtherScanAPI.Builder withRetryOnRateLimit(@Range(from = 0, to = 20) int maxRetryCount);

        @NotNull
        EtherScanAPI build();
    }
}
