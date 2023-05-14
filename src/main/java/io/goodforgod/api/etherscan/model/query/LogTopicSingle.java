package io.goodforgod.api.etherscan.model.query;

import static io.goodforgod.api.etherscan.model.query.LogQueryParams.*;

import io.goodforgod.api.etherscan.LogsAPI;
import io.goodforgod.api.etherscan.error.EtherScanLogQueryException;
import org.jetbrains.annotations.NotNull;

/**
 * Single topic parameter builder for The Event Log API
 *
 * @see LogQueryBuilderImpl
 * @see LogsAPI
 * @author GoodforGod
 * @since 31.10.2018
 */
public final class LogTopicSingle implements LogTopicBuilder {

    private final String address;
    private final long startBlock, endBlock;

    private final String topic0;

    LogTopicSingle(String address, long startBlock, long endBlock, String topic0) {
        this.address = address;
        this.startBlock = startBlock;
        this.endBlock = endBlock;
        this.topic0 = topic0;
    }

    @Override
    public @NotNull LogQuery build() throws EtherScanLogQueryException {
        return new LogQueryImpl(ADDRESS_PARAM + address
                + FROM_BLOCK_PARAM + startBlock + TO_BLOCK_PARAM + endBlock
                + TOPIC_0_PARAM + topic0);
    }
}
