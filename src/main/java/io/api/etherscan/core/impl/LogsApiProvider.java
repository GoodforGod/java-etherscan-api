package io.api.etherscan.core.impl;

import io.api.etherscan.core.ILogsApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.model.Log;
import io.api.etherscan.model.query.impl.LogQuery;
import io.api.etherscan.model.utility.LogResponseTO;
import io.api.etherscan.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Logs API Implementation
 *
 * @see ILogsApi
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class LogsApiProvider extends BasicProvider implements ILogsApi {

    private static final String ACT_LOGS_PARAM = ACT_PREFIX + "getLogs";

    LogsApiProvider(final IQueueManager queue,
                    final String baseUrl,
                    final IHttpExecutor executor) {
        super(queue, "logs", baseUrl, executor);
    }

    @NotNull
    @Override
    public List<Log> logs(final LogQuery query) throws ApiException {
        final String urlParams = ACT_LOGS_PARAM + query.getParams();
        final LogResponseTO response = getRequest(urlParams, LogResponseTO.class);
        BasicUtils.validateTxResponse(response);

        return (BasicUtils.isEmpty(response.getResult()))
                ? Collections.emptyList()
                : response.getResult();
    }
}
