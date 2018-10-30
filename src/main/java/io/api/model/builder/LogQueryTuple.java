package io.api.model.builder;

import io.api.error.LogQueryException;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogQueryTuple extends BaseLogQuery implements IQueryBuilder {

    private final String address;
    private final long startBlock, endBlock;
    private final String topic0, topic1;

    private LogOp topic0_1_opr;

    LogQueryTuple(String address, long startBlock, long endBlock,
                  String topic0, String topic1) {
        this.address = address;
        this.startBlock = startBlock;
        this.endBlock = endBlock;
        this.topic0 = topic0;
        this.topic1 = topic1;
    }

    public LogQueryTuple setOpTopic0_1(LogOp topic0_1_opr) {
        this.topic0_1_opr = topic0_1_opr;
        return this;
    }

    @Override
    public LogQueryFinal build() throws LogQueryException {
        if(topic0_1_opr == null)
            throw new LogQueryException("topic0_1_opr can not be null.");
        return null;
    }
}
