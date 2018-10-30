package io.api.core.impl;

import com.google.gson.Gson;
import io.api.error.ConnectionException;
import io.api.error.ParseException;
import io.api.executor.HttpExecutor;
import io.api.manager.IQueueManager;

import java.io.IOException;
import java.util.Map;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
abstract class BasicProvider {

    static final String ACTION_PARAM = "&action=";

    private static final String MODULE_PARAM = "&module=";
    private final String module;

    private final String baseUrl;
    private final Map<String, String> headers;
    private final HttpExecutor executor;
    private final IQueueManager queue;

    BasicProvider(final IQueueManager queue,
                  final String module,
                  final String baseUrl,
                  final Map<String, String> headers) {
        this.queue = queue;
        this.module = MODULE_PARAM + module;
        this.baseUrl = baseUrl;
        this.headers = headers;
        this.executor = new HttpExecutor();
    }

    private <T> T convert(final String json, final Class<T> tClass) {
        try {
            return new Gson().fromJson(json, tClass);
        } catch (Exception e) {
            throw new ParseException(e.getMessage(), e.getCause());
        }
    }

    private String getRequest(final String urlParameters) {
        try {
            queue.takeTurn();
            final String fullUrl = baseUrl + module + urlParameters;
            return executor.get(fullUrl, headers);
        } catch (IOException e) {
            throw new ConnectionException(e.getLocalizedMessage(), e.getCause());
        }
    }

    <T> T getRequest(final String urlParameters, final Class<T> tClass) {
        return convert(getRequest(urlParameters), tClass);
    }
}
