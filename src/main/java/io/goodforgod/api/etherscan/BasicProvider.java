package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanParseException;
import io.goodforgod.api.etherscan.error.EtherScanRateLimitException;
import io.goodforgod.api.etherscan.error.EtherScanResponseException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.response.StringResponseTO;
import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * Base provider for API Implementations
 *
 * @author GoodforGod
 * @see EtherScanAPIProvider
 * @since 28.10.2018
 */
abstract class BasicProvider {

    private static final String MAX_RATE_LIMIT_REACHED = "Max rate limit reached";

    static final int MAX_END_BLOCK = Integer.MAX_VALUE;
    static final int MIN_START_BLOCK = 0;

    static final String ACT_PREFIX = "&action=";

    private final String module;
    private final String baseUrl;
    private final EthHttpClient executor;
    private final RequestQueueManager queue;
    private final Converter converter;
    private final int retryCountLimit;

    BasicProvider(RequestQueueManager requestQueueManager,
                  String module,
                  String baseUrl,
                  EthHttpClient ethHttpClient,
                  Converter converter,
                  int retryCountLimit) {
        this.queue = requestQueueManager;
        this.module = "&module=" + module;
        this.baseUrl = baseUrl;
        this.executor = ethHttpClient;
        this.converter = converter;
        this.retryCountLimit = retryCountLimit;
    }

    private <T> T convert(byte[] json, Class<T> tClass) {
        try {
            final T t = converter.fromJson(json, tClass);
            if (t instanceof StringResponseTO && ((StringResponseTO) t).getResult().startsWith(MAX_RATE_LIMIT_REACHED)) {
                throw new EtherScanRateLimitException(((StringResponseTO) t).getResult());
            }

            return t;
        } catch (Exception e) {
            final StringResponseTO response = converter.fromJson(json, StringResponseTO.class);
            if (response.getResult() != null && response.getStatus() == 0) {
                if (response.getResult().startsWith(MAX_RATE_LIMIT_REACHED)) {
                    throw new EtherScanRateLimitException(response.getResult());
                } else {
                    throw new EtherScanResponseException(response);
                }
            }

            final String jsonAsString = new String(json, StandardCharsets.UTF_8);
            throw new EtherScanParseException(e.getMessage() + ", for response: " + jsonAsString, e.getCause(), jsonAsString);
        }
    }

    private byte[] getRequest(String urlParameters) {
        queue.takeTurn();
        final URI uri = URI.create(baseUrl + module + urlParameters);
        return executor.get(uri);
    }

    private byte[] postRequest(String urlParameters, String dataToPost) {
        queue.takeTurn();
        final URI uri = URI.create(baseUrl + module + urlParameters);
        return executor.post(uri, dataToPost.getBytes(StandardCharsets.UTF_8));
    }

    <T> T getRequest(String urlParameters, Class<T> tClass) {
        return getRequest(urlParameters, tClass, 0);
    }

    private <T> T getRequest(String urlParameters, Class<T> tClass, int retryCount) {
        try {
            return convert(getRequest(urlParameters), tClass);
        } catch (Exception e) {
            if (retryCount < retryCountLimit) {
                try {
                    Thread.sleep(1150);
                } catch (InterruptedException ex) {
                    throw new IllegalStateException(ex);
                }

                return getRequest(urlParameters, tClass, retryCount + 1);
            } else {
                throw e;
            }
        }
    }

    <T> T postRequest(String urlParameters, String dataToPost, Class<T> tClass) {
        return postRequest(urlParameters, dataToPost, tClass, 0);
    }

    private <T> T postRequest(String urlParameters, String dataToPost, Class<T> tClass, int retryCount) {
        try {
            return convert(postRequest(urlParameters, dataToPost), tClass);
        } catch (EtherScanRateLimitException e) {
            if (retryCount < retryCountLimit) {
                try {
                    Thread.sleep(1150);
                } catch (InterruptedException ex) {
                    throw new IllegalStateException(ex);
                }

                return postRequest(urlParameters, dataToPost, tClass, retryCount + 1);
            } else {
                throw e;
            }
        }
    }
}
