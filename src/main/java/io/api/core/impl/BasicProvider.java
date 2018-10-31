package io.api.core.impl;

import com.google.gson.Gson;
import io.api.error.ParseException;
import io.api.executor.IHttpExecutor;
import io.api.manager.IQueueManager;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
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

    BasicProvider(final IQueueManager queue,
                  final String module,
                  final String baseUrl,
                  final IHttpExecutor executor) {
        this.queue = queue;
        this.module = "&module=" + module;
        this.baseUrl = baseUrl;
        this.executor = executor;
    }

    private <T> T convert(final String json, final Class<T> tClass) {
        try {
            return new Gson().fromJson(json, tClass);
        } catch (Exception e) {
            throw new ParseException(e.getMessage(), e.getCause());
        }
    }

    private String getRequest(final String urlParameters) {
        queue.takeTurn();
        final String url = baseUrl + module + urlParameters;
        return executor.get(url);
    }

    <T> T getRequest(final String urlParameters, final Class<T> tClass) {
        return convert(getRequest(urlParameters), tClass);
    }
}
