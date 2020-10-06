package io.api.etherscan.core.impl;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.error.ParseException;
import io.api.etherscan.error.RateLimitException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
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

    static final int MAX_END_BLOCK = 999999999;
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
            return gson.fromJson(json, tClass);
        } catch (Exception e) {
            if (e instanceof JsonSyntaxException) {
                Map<String, Object> map = gson.fromJson(json, Map.class);
                Object statusCode = map.get("status");
                if ((statusCode instanceof String) && (statusCode.equals("0"))) {
                    Object message = map.get("message");
                    if ((message instanceof String) && (message.equals("NOTOK"))) {
                        Object result = map.get("result");
                        if ((result instanceof String) && (result.equals("Max rate limit reached"))) {
                            throw new RateLimitException ("Max rate limit reached");
                        }
                    }
                }
            }
            throw new ParseException(e.getMessage(), e.getCause(), json);
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
