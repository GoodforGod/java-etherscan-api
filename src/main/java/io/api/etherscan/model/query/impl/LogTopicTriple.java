package io.api.etherscan.model.query.impl;

import io.api.etherscan.core.ILogsApi;
import io.api.etherscan.error.LogQueryException;
import io.api.etherscan.model.query.IQueryBuilder;
import io.api.etherscan.model.query.LogOp;

/**
 * Triple topic parameter builder for The Event Log API
 *
 * @see LogQueryBuilder
 * @see ILogsApi
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogTopicTriple extends BaseLogQuery implements IQueryBuilder {

    private final String address;
    private final long startBlock, endBlock;
    private final String topic0, topic1, topic2;

    private LogOp topic0_1_opr, topic1_2_opr, topic0_2_opr;

    LogTopicTriple(String address, long startBlock, long endBlock,
                   String topic0, String topic1, String topic2) {
        this.address = address;
        this.startBlock = startBlock;
        this.endBlock = endBlock;
        this.topic0 = topic0;
        this.topic1 = topic1;
        this.topic2 = topic2;
    }

    public LogTopicTriple setOpTopic0_1(LogOp topic0_1_opr) {
        this.topic0_1_opr = topic0_1_opr;
        return this;
    }

    public LogTopicTriple setOpTopic0_2(LogOp topic0_2_opr) {
        this.topic0_2_opr = topic0_2_opr;
        return this;
    }

    public LogTopicTriple setOpTopic1_2(LogOp topic1_2_opr) {
        this.topic1_2_opr = topic1_2_opr;
        return this;
    }

    @Override
    public LogQuery build() throws LogQueryException {
        if (topic0_1_opr == null)
            throw new LogQueryException("topic0_1_opr can not be null.");
        if (topic0_2_opr == null)
            throw new LogQueryException("topic0_2_opr can not be null.");
        if (topic1_2_opr == null)
            throw new LogQueryException("topic1_2_opr can not be null.");

        return new LogQuery(ADDRESS_PARAM + address
                + FROM_BLOCK_PARAM + startBlock + TO_BLOCK_PARAM + endBlock
                + TOPIC_0_PARAM + topic0
                + TOPIC_1_PARAM + topic1
                + TOPIC_2_PARAM + topic2
                + TOPIC_0_1_OPR_PARAM + topic0_1_opr.getOperation()
                + TOPIC_1_2_OPR_PARAM + topic1_2_opr.getOperation()
                + TOPIC_0_2_OPR_PARAM + topic0_2_opr.getOperation());
    }
}
