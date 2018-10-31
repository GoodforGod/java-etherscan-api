package io.api.core;

import io.api.model.Log;
import io.api.model.builder.LogQuery;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#logs
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface ILogsProvider {

    @NotNull List<Log> logs(LogQuery query);
}
