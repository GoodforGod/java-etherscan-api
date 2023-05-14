package io.goodforgod.api.etherscan;

import com.google.gson.Gson;
import io.goodforgod.api.etherscan.error.EtherScanKeyException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.http.impl.UrlEthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.util.BasicUtils;
import io.goodforgod.gson.configuration.GsonConfiguration;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 11.05.2023
 */
final class EthScanAPIBuilder implements EtherScanAPI.Builder {

    private static final Supplier<EthHttpClient> DEFAULT_SUPPLIER = UrlEthHttpClient::new;
    private static final String DEFAULT_KEY = "YourApiKeyToken";

    private final Gson gson = new GsonConfiguration().builder().create();

    private String apiKey = DEFAULT_KEY;
    private EthNetwork ethNetwork = EthNetworks.MAINNET;
    private RequestQueueManager queueManager = RequestQueueManager.ANONYMOUS;
    private Supplier<EthHttpClient> ethHttpClientSupplier = DEFAULT_SUPPLIER;
    private Supplier<Converter> converterSupplier = () -> new Converter() {

        @Override
        public <T> @NotNull T fromJson(@NotNull String json, @NotNull Class<T> type) {
            return gson.fromJson(json, type);
        }
    };

    @NotNull
    @Override
    public EtherScanAPI.Builder withApiKey(@NotNull String apiKey) {
        if (BasicUtils.isBlank(apiKey))
            throw new EtherScanKeyException("API key can not be null or empty");

        this.apiKey = apiKey;
        if (!DEFAULT_KEY.equals(apiKey)) {
            queueManager = RequestQueueManager.UNLIMITED;
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

    @NotNull
    @Override
    public EtherScanAPI.Builder withConverter(@NotNull Supplier<Converter> converterSupplier) {
        this.converterSupplier = converterSupplier;
        return this;
    }

    @Override
    public @NotNull EtherScanAPI build() {
        return new EtherScanAPIProvider(apiKey, ethNetwork, queueManager, ethHttpClientSupplier.get(), converterSupplier.get());
    }
}
