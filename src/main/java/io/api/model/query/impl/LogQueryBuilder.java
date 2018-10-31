package io.api.model.query.impl;

import io.api.error.LogQueryException;
import io.api.util.BasicUtils;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogQueryBuilder {

    private static final long MIN_BLOCK = 0;
    private static final long MAX_BLOCK = 99999999999L;

    private final String address;
    private final long startBlock, endBlock;

    private LogQueryBuilder(String address, long startBlock, long endBlock) {
        this.address = address;
        this.startBlock = startBlock;
        this.endBlock = endBlock;
    }

    public static LogQueryBuilder with(String address) {
        return with(address, MIN_BLOCK);
    }

    public static LogQueryBuilder with(String address, long startBlock) {
        return with(address, startBlock, MAX_BLOCK);
    }

    public static LogQueryBuilder with(String address, long startBlock, long endBlock) {
        return new LogQueryBuilder(address, startBlock, endBlock);
    }

    public LogTopicSingle topic(String topic0) {
        if(BasicUtils.isEmpty(topic0))
            throw new LogQueryException("topic0 can not be empty.");
        return new LogTopicSingle(address, startBlock, endBlock, topic0);
    }

    public LogTopicTuple topic(String topic0, String topic1) {
        if(BasicUtils.isEmpty(topic0))
            throw new LogQueryException("topic0 can not be empty.");
        if(BasicUtils.isEmpty(topic1))
            throw new LogQueryException("topic1 can not be empty.");
        return new LogTopicTuple(address, startBlock, endBlock, topic0, topic1);
    }

    public LogTopicTriple topic(String topic0, String topic1, String topic2) {
        if(BasicUtils.isEmpty(topic0))
            throw new LogQueryException("topic0 can not be empty.");
        if(BasicUtils.isEmpty(topic1))
            throw new LogQueryException("topic1 can not be empty.");
        if(BasicUtils.isEmpty(topic2))
            throw new LogQueryException("topic2 can not be empty.");
        return new LogTopicTriple(address, startBlock, endBlock, topic0, topic1, topic2);
    }

    public LogTopicQuadro topic(String topic0, String topic1, String topic2, String topic3) {
        if(BasicUtils.isEmpty(topic0))
            throw new LogQueryException("topic0 can not be empty.");
        if(BasicUtils.isEmpty(topic1))
            throw new LogQueryException("topic1 can not be empty.");
        if(BasicUtils.isEmpty(topic2))
            throw new LogQueryException("topic2 can not be empty.");
        if(BasicUtils.isEmpty(topic3))
            throw new LogQueryException("topic3 can not be empty.");

        return new LogTopicQuadro(address, startBlock, endBlock, topic0, topic1, topic2, topic3);
    }
}
