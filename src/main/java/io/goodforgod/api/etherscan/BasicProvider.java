package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.error.EtherScanParseException;
import io.goodforgod.api.etherscan.error.EtherScanRateLimitException;
import io.goodforgod.api.etherscan.error.EtherScanResponseException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.response.StringResponseTO;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Base provider for API Implementations
 *
 * @author GoodforGod
 * @see EtherScanAPIProvider
 * @since 28.10.2018
 */
abstract class BasicProvider {

    static final int MAX_END_BLOCK = Integer.MAX_VALUE;
    static final int MIN_START_BLOCK = 0;

    static final String ACT_PREFIX = "&action=";

    private final String module;
    private final String baseUrl;
    private final EthHttpClient executor;
    private final RequestQueueManager queue;
    private final Converter converter;

    BasicProvider(RequestQueueManager requestQueueManager,
                  String module,
                  String baseUrl,
                  EthHttpClient ethHttpClient,
                  Converter converter) {
        this.queue = requestQueueManager;
        this.module = "&module=" + module;
        this.baseUrl = baseUrl;
        this.executor = ethHttpClient;
        this.converter = converter;
    }

    <T> T convert(byte[] json, Class<T> tClass) {
        try {
            final T t = converter.fromJson(json, tClass);
            if (t instanceof StringResponseTO && ((StringResponseTO) t).getResult().startsWith("Max rate limit reached")) {
                throw new EtherScanRateLimitException(((StringResponseTO) t).getResult());
            }

            return t;
        } catch (Exception e) {
            final String jsonAsString = new String(json, StandardCharsets.UTF_8);
            try {
                final Map<String, Object> map = converter.fromJson(json, Map.class);
                final Object result = map.get("result");
                if (result instanceof String && ((String) result).startsWith("Max rate limit reached"))
                    throw new EtherScanRateLimitException(((String) result));

                throw new EtherScanParseException(e.getMessage() + ", for response: " + jsonAsString, e.getCause(), jsonAsString);
            } catch (EtherScanException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new EtherScanParseException(e.getMessage() + ", for response: " + jsonAsString, e.getCause(), jsonAsString);
            }
        }
    }

    byte[] getRequest(String urlParameters) {
        queue.takeTurn();
        final URI uri = URI.create(baseUrl + module + urlParameters);
        final byte[] result = executor.get(uri);
        if (result.length == 0)
            throw new EtherScanResponseException("Server returned null value for GET request at URL - " + uri);

        return result;
    }

    byte[] postRequest(String urlParameters, String dataToPost) {
        queue.takeTurn();
        final URI uri = URI.create(baseUrl + module + urlParameters);
        return executor.post(uri, dataToPost.getBytes(StandardCharsets.UTF_8));
    }

    <T> T getRequest(String urlParameters, Class<T> tClass) {
        return convert(getRequest(urlParameters), tClass);
    }

    <T> T postRequest(String urlParameters, String dataToPost, Class<T> tClass) {
        return convert(postRequest(urlParameters, dataToPost), tClass);
    }
}
