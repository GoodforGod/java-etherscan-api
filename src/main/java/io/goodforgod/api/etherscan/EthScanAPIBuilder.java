package io.goodforgod.api.etherscan;

import com.google.gson.Gson;
import io.goodforgod.api.etherscan.error.EtherScanKeyException;
import io.goodforgod.api.etherscan.error.EtherScanParseException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.http.impl.JdkEthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.util.BasicUtils;
import io.goodforgod.gson.configuration.GsonConfiguration;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 11.05.2023
 */
public class EthScanAPIBuilder implements EtherScanAPI.Builder {

    private static final Supplier<EthHttpClient> DEFAULT_SUPPLIER = JdkEthHttpClient::new;
    private static final String DEFAULT_KEY = "YourApiKeyToken";

    private final Gson gson = new GsonConfiguration().builder().create();

    private int retryCountOnLimitReach = 0;
    private String apiKey = DEFAULT_KEY;
    private RequestQueueManager queueManager;
    private EthNetwork ethNetwork = EthNetworks.MAINNET;
    private Supplier<EthHttpClient> ethHttpClientSupplier = DEFAULT_SUPPLIER;
    private Supplier<Converter> converterSupplier = () -> new Converter() {

        @Override
        public <T> @NotNull T fromJson(byte[] jsonAsByteArray, @NotNull Class<T> type) {
            try (InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(jsonAsByteArray))) {
                return gson.fromJson(isr, type);
            } catch (IOException e) {
                throw new EtherScanParseException(e.getMessage(), e, new String(jsonAsByteArray, StandardCharsets.UTF_8));
            }
        }
    };

    @NotNull
    @Override
    public EtherScanAPI.Builder withApiKey(@NotNull String apiKey) {
        if (BasicUtils.isBlank(apiKey))
            throw new EtherScanKeyException("API key can not be null or empty");

        this.apiKey = apiKey;
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

    @NotNull
    public EtherScanAPI.Builder withRetryOnRateLimit(int maxRetryCount) {
        if (maxRetryCount < 0 || maxRetryCount > 20) {
            throw new IllegalStateException("maxRetryCount value must be in range from 0 to 20, but was: " + maxRetryCount);
        }

        this.retryCountOnLimitReach = maxRetryCount;
        return this;
    }

    @Override
    public @NotNull EtherScanAPI build() {
        RequestQueueManager requestQueueManager;
        if (queueManager != null) {
            requestQueueManager = queueManager;
        } else if (DEFAULT_KEY.equals(apiKey)) {
            requestQueueManager = RequestQueueManager.anonymous();
        } else {
            requestQueueManager = RequestQueueManager.planFree();
        }

        return new EtherScanAPIProvider(apiKey, ethNetwork, requestQueueManager, ethHttpClientSupplier.get(),
                converterSupplier.get(), retryCountOnLimitReach);
    }
}
