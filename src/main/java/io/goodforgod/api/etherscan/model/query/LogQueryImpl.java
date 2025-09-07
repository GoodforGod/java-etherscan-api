package io.goodforgod.api.etherscan.model.query;

import io.goodforgod.api.etherscan.LogsAPI;
import org.jetbrains.annotations.NotNull;

/**
 * Final builded container for The Event Log API
 * EtherScan - API Descriptions <a href="https://etherscan.io/apis#logs">...</a>
 *
 * @see LogQueryBuilderImpl
 * @see LogsAPI
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogQueryImpl implements LogQuery {

    /**
     * Final request parameter for api call
     */
    private final String params;

    LogQueryImpl(String params) {
        this.params = params;
    }

    @Override
    public @NotNull String params() {
        return params;
    }
}
