package io.api.model.builder;

import io.api.error.LogQueryException;
import io.api.util.BasicUtils;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogQuery {

    private final String address;
    private final long startBlock, endBlock;

    public LogQuery(String address, long startBlock, long endBlock) {
        this.address = address;
        this.startBlock = startBlock;
        this.endBlock = endBlock;
    }

    public LogQuerySingle topic(String topic0) {
        if(BasicUtils.isEmpty(topic0))
            throw new LogQueryException("topic0 can not be empty.");
        return new LogQuerySingle(address, startBlock, endBlock, topic0);
    }

    public LogQueryTuple topic(String topic0, String topic1) {
        if(BasicUtils.isEmpty(topic0))
            throw new LogQueryException("topic0 can not be empty.");
        if(BasicUtils.isEmpty(topic1))
            throw new LogQueryException("topic1 can not be empty.");
        return new LogQueryTuple(address, startBlock, endBlock, topic0, topic1);
    }

    public LogQueryTriple topic(String topic0, String topic1, String topic2) {
        if(BasicUtils.isEmpty(topic0))
            throw new LogQueryException("topic0 can not be empty.");
        if(BasicUtils.isEmpty(topic1))
            throw new LogQueryException("topic1 can not be empty.");
        if(BasicUtils.isEmpty(topic2))
            throw new LogQueryException("topic2 can not be empty.");
        return new LogQueryTriple(address, startBlock, endBlock, topic0, topic1, topic2);
    }

    public LogQueryQuadro topic(String topic0, String topic1, String topic2, String topic3) {
        if(BasicUtils.isEmpty(topic0))
            throw new LogQueryException("topic0 can not be empty.");
        if(BasicUtils.isEmpty(topic1))
            throw new LogQueryException("topic1 can not be empty.");
        if(BasicUtils.isEmpty(topic2))
            throw new LogQueryException("topic2 can not be empty.");
        if(BasicUtils.isEmpty(topic3))
            throw new LogQueryException("topic3 can not be empty.");

        return new LogQueryQuadro(address, startBlock, endBlock, topic0, topic1, topic2, topic3);
    }
}
