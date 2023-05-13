package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanKeyException;
import io.goodforgod.api.etherscan.executor.EthHttpClient;
import io.goodforgod.api.etherscan.executor.impl.UrlEthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.manager.impl.FakeRequestQueueManager;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 11.05.2023
 */
final class EthScanAPIBuilder implements EtherScanAPI.Builder {

    private static final Supplier<EthHttpClient> DEFAULT_SUPPLIER = UrlEthHttpClient::new;
    private static final String DEFAULT_KEY = "YourApiKeyToken";

    private String apiKey = DEFAULT_KEY;
    private EthNetwork ethNetwork = EthNetworks.MAINNET;
    private RequestQueueManager queueManager = RequestQueueManager.DEFAULT;
    private Supplier<EthHttpClient> ethHttpClientSupplier = DEFAULT_SUPPLIER;

    @NotNull
    @Override
    public EtherScanAPI.Builder withApiKey(@NotNull String apiKey) {
        if (BasicUtils.isBlank(apiKey))
            throw new EtherScanKeyException("API key can not be null or empty");

        this.apiKey = apiKey;
        if (!DEFAULT_KEY.equals(apiKey)) {
            queueManager = new FakeRequestQueueManager();
        }
        return this;
    }

    @NotNull
    @Override
    public EtherScanAPI.Builder withNetwork(@NotNull EthNetwork network) {
        this.ethNetwork = network;
        return this;
    }

    @NotNull
    @Override
    public EtherScanAPI.Builder withNetwork(@NotNull EthNetworks network) {
        this.ethNetwork = network;
        return this;
    }

    @NotNull
    @Override
    public EtherScanAPI.Builder withQueue(@NotNull RequestQueueManager queueManager) {
        this.queueManager = queueManager;
        return this;
    }

    @NotNull
    @Override
    public EtherScanAPI.Builder withHttpClient(@NotNull Supplier<EthHttpClient> httpClientSupplier) {
        this.ethHttpClientSupplier = httpClientSupplier;
        return this;
    }

    @Override
    public @NotNull EtherScanAPI build() {
        return new EtherScanAPIProvider(apiKey, ethNetwork, ethHttpClientSupplier, queueManager);
    }
}
