package io.goodforgod.api.etherscan.model.query;

import static io.goodforgod.api.etherscan.model.query.LogQueryParams.*;

import io.goodforgod.api.etherscan.LogsAPI;
import io.goodforgod.api.etherscan.error.EtherScanLogQueryException;
import org.jetbrains.annotations.NotNull;

/**
 * Tuple topic parameter builder for The Event Log API
 *
 * @see LogQueryBuilderImpl
 * @see LogsAPI
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogTopicTuple implements LogTopicBuilder {

    private final String address;
    private final long startBlock, endBlock;
    private final String topic0, topic1;

    private LogOp topic0_1_opr;

    LogTopicTuple(String address,
                  long startBlock,
                  long endBlock,
                  String topic0,
                  String topic1) {
        this.address = address;
        this.startBlock = startBlock;
        this.endBlock = endBlock;
        this.topic0 = topic0;
        this.topic1 = topic1;
    }

    public LogTopicTuple setOpTopic0_1(LogOp topic0_1_opr) {
        this.topic0_1_opr = topic0_1_opr;
        return this;
    }

    @Override
    public @NotNull LogQuery build() throws EtherScanLogQueryException {
        if (topic0_1_opr == null)
            throw new EtherScanLogQueryException("topic0_1_opr can not be null.");

        return new LogQueryImpl(ADDRESS_PARAM + address
                + FROM_BLOCK_PARAM + startBlock + TO_BLOCK_PARAM + endBlock
                + TOPIC_0_PARAM + topic0
                + TOPIC_1_PARAM + topic1
                + TOPIC_0_1_OPR_PARAM + topic0_1_opr.getOperation());
    }
}
