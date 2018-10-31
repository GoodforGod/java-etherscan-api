package io.api.model.builder;

import io.api.error.LogQueryException;

/**
 * ! NO DESCRIPTION !
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
