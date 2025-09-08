package io.goodforgod.api.etherscan.model.query;

import io.goodforgod.api.etherscan.LogsAPI;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LogQueryImpl logQuery = (LogQueryImpl) o;
        return Objects.equals(params, logQuery.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(params);
    }

    @Override
    public String toString() {
        return "LogQueryImpl{" +
                "params=" + params + '\'' +
                '}';
    }
}
