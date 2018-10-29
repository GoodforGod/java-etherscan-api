package io.api.core.impl;

import com.jsoniter.JsonIterator;
import io.api.error.ConnectionException;
import io.api.error.ParseException;
import io.api.executor.HttpExecutor;

import java.io.IOException;
import java.util.Map;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
abstract class BasicProvider {

    private static final String moduleParam = "&module=";
    private final String module;

    private final String baseUrl;
    private final Map<String, String> headers;
    private final HttpExecutor executor;

    BasicProvider(final String module,
                  final String baseUrl,
                  final Map<String, String> headers) {
        this.module = module;
        this.baseUrl = baseUrl;
        this.headers = headers;
        this.executor = new HttpExecutor();
    }

    private String getModuleParam() {
        return moduleParam + module;
    }

    <T> T convert(String json, Class<T> tClass) {
        try {
            return JsonIterator.deserialize(json, tClass);
        } catch (Exception e) {
            throw new ParseException(e.getMessage(), e.getCause());
        }
    }

    String getRequest(final String urlParameters) {
        try {
            final String fullUrl = baseUrl + getModuleParam() + urlParameters;
            return executor.get(fullUrl, headers);
        } catch (IOException e) {
            throw new ConnectionException(e.getLocalizedMessage(), e.getCause());
        }
    }
}
