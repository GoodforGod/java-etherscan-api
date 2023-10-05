package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.Log;
import io.goodforgod.api.etherscan.model.query.LogQuery;
import io.goodforgod.api.etherscan.model.response.LogResponseTO;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Logs API Implementation
 *
 * @see LogsAPI
 * @author GoodforGod
 * @since 28.10.2018
 */
final class LogsAPIProvider extends BasicProvider implements LogsAPI {

    private static final String ACT_LOGS_PARAM = ACT_PREFIX + "getLogs";

    LogsAPIProvider(RequestQueueManager queue,
                    String baseUrl,
                    EthHttpClient executor,
                    Converter converter,
                    int retryCount) {
        super(queue, "logs", baseUrl, executor, converter, retryCount);
    }

    @NotNull
    @Override
    public List<Log> logs(@NotNull LogQuery query) throws EtherScanException {
        final String urlParams = ACT_LOGS_PARAM + query.params();
        final LogResponseTO response = getRequest(urlParams, LogResponseTO.class);
        BasicUtils.validateTxResponse(response);

        return (BasicUtils.isEmpty(response.getResult()))
                ? Collections.emptyList()
                : response.getResult();
    }
}
