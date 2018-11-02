package io.api.etherscan.model.query.impl;

import io.api.etherscan.core.ILogsApi;
import io.api.etherscan.error.LogQueryException;
import io.api.etherscan.model.query.IQueryBuilder;

/**
 * Single topic parameter builder for The Event Log API
 *
 * @see LogQueryBuilder
 * @see ILogsApi
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogTopicSingle extends BaseLogQuery implements IQueryBuilder {

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
    public LogQuery build() throws LogQueryException {
        return new LogQuery(ADDRESS_PARAM + address
                + FROM_BLOCK_PARAM + startBlock + TO_BLOCK_PARAM + endBlock
                + TOPIC_0_PARAM + topic0);
    }
}
