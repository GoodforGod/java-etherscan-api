package io.api.etherscan.core.impl;

import io.api.etherscan.core.IEventsApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.model.event.IEvent;
import io.api.etherscan.model.query.impl.LogQuery;
import io.api.etherscan.model.utility.LogResponseTO;
import io.api.etherscan.util.BasicUtils;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Logs API Implementation
 *
 * @see IEventsApi
 *
 */
@Experimental
public class EventsApiProvider extends BasicProvider implements IEventsApi {

    private static final String ACT_LOGS_PARAM = ACT_PREFIX + "getLogs";

    EventsApiProvider(final IQueueManager queue,
                      final String baseUrl,
                      final IHttpExecutor executor) {
        super(queue, "logs", baseUrl, executor);
    }

    @Experimental
    @NotNull
    @Override
    public List<IEvent> events(final LogQuery query) throws ApiException {
        final String urlParams = ACT_LOGS_PARAM + query.getParams();
        final LogResponseTO response = getRequest(urlParams, LogResponseTO.class);
        BasicUtils.validateTxResponse(response);

        if (BasicUtils.isEmpty(response.getResult())) {
            return Collections.emptyList();
        } ;
        return response
                .getResult()
                .stream()
                .map((log) -> {
                    String eventTypeHash = log.getTopics().get(0);
                    return IEvent.createEvent(eventTypeHash, log);
                })
                .collect(Collectors.toList());
    }
}
