package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.error.EtherScanParseException;
import io.goodforgod.api.etherscan.error.EtherScanRateLimitException;
import io.goodforgod.api.etherscan.error.EtherScanResponseException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.http.EthResponse;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.response.BaseListResponseTO;
import io.goodforgod.api.etherscan.model.response.StringResponseTO;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.ApiStatus.Internal;

/**
 * Base provider for API Implementations
 *
 * @author GoodforGod
 * @see EtherScanAPIProvider
 * @since 28.10.2018
 */
@Internal
public abstract class BasicProvider {

    private static final String MAX_RATE_LIMIT_REACHED = "Max rate limit reached";

    private static final int OFFSET_MAX = 9999;

    static final int MAX_END_BLOCK = Integer.MAX_VALUE;
    static final int MIN_START_BLOCK = 0;

    static final String ACT_PREFIX = "&action=";

    private final String module;
    private final String baseUrl;
    private final EthHttpClient executor;
    private final RequestQueueManager queue;
    private final Converter converter;
    private final int retryCountLimit;

    public BasicProvider(RequestQueueManager requestQueueManager,
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

    protected <T> T convert(byte[] json, Class<T> tClass) {
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

    protected int getMaximumOffset() {
        return OFFSET_MAX;
    }

    /**
     * Generic search for txs using offset api param To avoid 10k limit per response
     *
     * @param urlParams Url params for #getRequest()
     * @param tClass    responseListTO class
     * @param <T>       responseTO list T type
     * @param <R>       responseListTO type
     * @return List of T values
     */
    protected <T, R extends BaseListResponseTO<T>> List<T> getRequestUsingOffset(final String urlParams, Class<R> tClass)
            throws EtherScanException {
        final List<T> result = new ArrayList<>();
        int page = 1;
        while (true) {
            final String formattedUrl = String.format(urlParams, page++);
            final R response = getResponse(formattedUrl, tClass);
            BasicUtils.validateTxResponse(response);
            if (BasicUtils.isEmpty(response.getResult()))
                break;

            result.addAll(response.getResult());
            if (response.getResult().size() < getMaximumOffset())
                break;
        }

        return result;
    }

    protected EthResponse getResponse(String urlParameters) {
        queue.takeTurn();
        final URI uri = URI.create(baseUrl + module + urlParameters);
        return executor.get(uri);
    }

    protected EthResponse postRequest(String urlParameters, String dataToPost) {
        queue.takeTurn();
        final URI uri = URI.create(baseUrl + module + urlParameters);
        return executor.post(uri, dataToPost.getBytes(StandardCharsets.UTF_8));
    }

    protected <T> T getResponse(String urlParameters, Class<T> tClass) {
        return getResponse(urlParameters, tClass, 0);
    }

    protected <T> T getResponse(String urlParameters, Class<T> tClass, int retryCount) {
        try {
            System.out.println("URL - " + URI.create(baseUrl + module + urlParameters));
            EthResponse response = getResponse(urlParameters);
            System.out.println("Response - " + new String(response.body(), StandardCharsets.UTF_8));
            return convert(response.body(), tClass);
        } catch (Exception e) {
            if (retryCount < retryCountLimit) {
                try {
                    Thread.sleep(1150);
                } catch (InterruptedException ex) {
                    throw new IllegalStateException(ex);
                }

                return getResponse(urlParameters, tClass, retryCount + 1);
            } else {
                throw e;
            }
        }
    }

    protected <T> T postRequest(String urlParameters, String dataToPost, Class<T> tClass) {
        return postRequest(urlParameters, dataToPost, tClass, 0);
    }

    protected <T> T postRequest(String urlParameters, String dataToPost, Class<T> tClass, int retryCount) {
        try {
            EthResponse response = postRequest(urlParameters, dataToPost);
            return convert(response.body(), tClass);
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
