package io.api.etherscan.model.query.impl;

import io.api.etherscan.core.ILogsApi;

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

    public String getParams() {
        return params;
    }
}
