package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.model.Log;
import io.goodforgod.api.etherscan.model.query.LogQuery;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions <a href="https://docs.etherscan.io/api-endpoints/logs">...</a>
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface LogsAPI {

    /**
     * alternative to the native eth_getLogs Read at EtherScan API description for full info!
     *
     * @param query build log query
     * @return logs according to query
     * @throws EtherScanException parent exception class
     * @see LogQuery
     */
    @NotNull
    List<Log> logs(LogQuery query) throws EtherScanException;
}
