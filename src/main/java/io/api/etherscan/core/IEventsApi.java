package io.api.etherscan.core;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.model.event.IEvent;
import io.api.etherscan.model.query.impl.LogQuery;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * EtherScan - API Descriptions https://etherscan.io/apis#logs
 */
public interface IEventsApi {

    /**
     * This is a high-level alternative to the ILogsApi and an alternative to the
     * native eth_getLogs Read at EtherScan API description for full info!
     * 
     * @param query build log query
     * @return logs according to query
     * @throws ApiException parent exception class
     *
     * @see io.api.etherscan.model.query.impl.LogQueryBuilder
     */
    @NotNull
    List<IEvent> events(LogQuery query) throws ApiException;
}
