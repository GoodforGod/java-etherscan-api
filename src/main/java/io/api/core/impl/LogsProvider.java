package io.api.core.impl;

import io.api.core.ILogsProvider;
import io.api.executor.IHttpExecutor;
import io.api.manager.IQueueManager;
import io.api.model.Log;
import io.api.model.builder.LogQuery;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class LogsProvider extends BasicProvider implements ILogsProvider {

    private static final String ACT_LOGS_PARAM = ACT_PARAM + "getLogs";

    LogsProvider(final IQueueManager queue,
                        final String baseUrl,
                        final IHttpExecutor executor) {
        super(queue, "logs", baseUrl,executor);
    }

    @NotNull
    @Override
    public List<Log> logs(final LogQuery query) {
        return null;
    }
}
