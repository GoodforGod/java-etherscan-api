package io.goodforgod.api.etherscan;

import com.google.gson.Gson;
import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.error.EtherScanParseException;
import io.goodforgod.api.etherscan.error.EtherScanRateLimitException;
import io.goodforgod.api.etherscan.error.EtherScanResponseException;
import io.goodforgod.api.etherscan.executor.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.response.StringResponseTO;
import io.goodforgod.api.etherscan.util.BasicUtils;
import io.goodforgod.gson.configuration.GsonConfiguration;
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
    private final Gson gson;

    BasicProvider(RequestQueueManager requestQueueManager,
                  String module,
                  String baseUrl,
                  EthHttpClient ethHttpClient) {
        this.queue = requestQueueManager;
        this.module = "&module=" + module;
        this.baseUrl = baseUrl;
        this.executor = ethHttpClient;
        this.gson = new GsonConfiguration().builder().create();
    }

    <T> T convert(String json, Class<T> tClass) {
        try {
            final T t = gson.fromJson(json, tClass);
            if (t instanceof StringResponseTO && ((StringResponseTO) t).getResult().startsWith("Max rate limit reached")) {
                throw new EtherScanRateLimitException(((StringResponseTO) t).getResult());
            }

            return t;
        } catch (Exception e) {
            try {
                final Map<String, Object> map = gson.fromJson(json, Map.class);
                final Object result = map.get("result");
                if (result instanceof String && ((String) result).startsWith("Max rate limit reached"))
                    throw new EtherScanRateLimitException(((String) result));

                throw new EtherScanParseException(e.getMessage() + ", for response: " + json, e.getCause(), json);
            } catch (EtherScanException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new EtherScanParseException(e.getMessage() + ", for response: " + json, e.getCause(), json);
            }
        }
    }

    String getRequest(String urlParameters) {
        queue.takeTurn();
        final String url = baseUrl + module + urlParameters;
        final String result = executor.get(url);
        if (BasicUtils.isEmpty(result))
            throw new EtherScanResponseException("Server returned null value for GET request at URL - " + url);

        return result;
    }

    String postRequest(String urlParameters, String dataToPost) {
        queue.takeTurn();
        final String url = baseUrl + module + urlParameters;
        return executor.post(url, dataToPost);
    }

    <T> T getRequest(String urlParameters, Class<T> tClass) {
        return convert(getRequest(urlParameters), tClass);
    }

    <T> T postRequest(String urlParameters, String dataToPost, Class<T> tClass) {
        return convert(postRequest(urlParameters, dataToPost), tClass);
    }
}
