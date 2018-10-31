package io.api.core.impl;

import io.api.core.ILogsProvider;
import io.api.executor.IHttpExecutor;
import io.api.manager.IQueueManager;
import io.api.model.Log;
import io.api.model.query.impl.LogQuery;
import io.api.model.utility.LogResponseTO;
import io.api.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class LogsProvider extends BasicProvider implements ILogsProvider {

    private static final String ACT_LOGS_PARAM = ACT_PREFIX + "getLogs";

    LogsProvider(final IQueueManager queue,
                 final String baseUrl,
                 final IHttpExecutor executor) {
        super(queue, "logs", baseUrl, executor);
    }

    @NotNull
    @Override
    public List<Log> logs(final LogQuery query) {
        final String urlParams = ACT_LOGS_PARAM + query.getParams();
        final LogResponseTO response = getRequest(urlParams, LogResponseTO.class);
        BasicUtils.validateTxResponse(response);

        return (BasicUtils.isEmpty(response.getResult()))
                ? Collections.emptyList()
                : response.getResult();
    }
}
