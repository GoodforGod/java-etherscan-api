package io.api.core.impl;

import io.api.manager.IQueueManager;

import java.util.Map;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class LogsProvider extends BasicProvider {

    public LogsProvider(final IQueueManager queue,
                        final String baseUrl,
                        final Map<String, String> headers) {
        super(queue, "", baseUrl, headers);
    }
}
