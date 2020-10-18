package io.api.etherscan.core.impl;

import com.google.gson.Gson;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.error.ParseException;
import io.api.etherscan.error.RateLimitException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.model.utility.StringResponseTO;
import io.api.etherscan.util.BasicUtils;

import java.util.Map;

/**
 * Base provider for API Implementations
 *
 * @author GoodforGod
 * @see EtherScanApi
 * @since 28.10.2018
 */
abstract class BasicProvider {

    static final int MAX_END_BLOCK = Integer.MAX_VALUE;
    static final int MIN_START_BLOCK = 0;

    static final String ACT_PREFIX = "&action=";

    private final String module;
    private final String baseUrl;
    private final IHttpExecutor executor;
    private final IQueueManager queue;
    private final Gson gson;

    BasicProvider(final IQueueManager queue,
                  final String module,
                  final String baseUrl,
                  final IHttpExecutor executor) {
        this.queue = queue;
        this.module = "&module=" + module;
        this.baseUrl = baseUrl;
        this.executor = executor;
        this.gson = new Gson();
    }

    <T> T convert(final String json, final Class<T> tClass) {
        try {
            final T t = gson.fromJson(json, tClass);
            if (t instanceof StringResponseTO && ((StringResponseTO) t).getResult().startsWith("Max rate limit reached")) {
                throw new RateLimitException(((StringResponseTO) t).getResult());
            }

            return t;
        } catch (Exception e) {
            try {
                final Map<String, Object> map = gson.fromJson(json, Map.class);
                final Object result = map.get("result");
                if (result instanceof String && ((String) result).startsWith("Max rate limit reached"))
                    throw new RateLimitException(((String) result));

                throw new ParseException(e.getMessage() + ", for response: " + json, e.getCause(), json);
            } catch (ApiException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new ParseException(e.getMessage() + ", for response: " + json, e.getCause(), json);
            }
        }
    }

    String getRequest(final String urlParameters) {
        queue.takeTurn();
        final String url = baseUrl + module + urlParameters;
        final String result = executor.get(url);
        if (BasicUtils.isEmpty(result))
            throw new EtherScanException("Server returned null value for GET request at URL - " + url);

        return result;
    }

    String postRequest(final String urlParameters, final String dataToPost) {
        queue.takeTurn();
        final String url = baseUrl + module + urlParameters;
        return executor.post(url, dataToPost);
    }

    <T> T getRequest(final String urlParameters, final Class<T> tClass) {
        return convert(getRequest(urlParameters), tClass);
    }

    <T> T postRequest(final String urlParameters, final String dataToPost, final Class<T> tClass) {
        return convert(postRequest(urlParameters, dataToPost), tClass);
    }
}
