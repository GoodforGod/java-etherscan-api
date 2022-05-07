package io.api.etherscan.model.query.impl;

import io.api.etherscan.core.ILogsApi;
import io.api.etherscan.model.query.impl.topic.TopicParams;
import org.jetbrains.annotations.NotNull;

/**
 * Final builded container for The Event Log API
 *
 * EtherScan - API Descriptions https://etherscan.io/apis#logs
 *
 * @see LogQueryBuilder
 * @see ILogsApi
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogQuery {

    /**
     * Final request parameter for api call
     */
    private final String params;

    LogQuery(String params) {
        this.params = params;
    }

    public LogQuery(@NotNull StandardLogQueryParams standardLogQueryParams, @NotNull TopicParams topicParams) {
        this.params = standardLogQueryParams.getApiParams() + topicParams.getApiParams();
    }

    public String getParams() {
        return params;
    }
}
