package io.goodforgod.api.etherscan.model.query;

import io.goodforgod.api.etherscan.LogsAPI;
import io.goodforgod.api.etherscan.error.ErtherScanLogQueryException;
import io.goodforgod.api.etherscan.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Builder for The Event Log API
 *
 * @see LogsAPI
 * @author GoodforGod
 * @since 31.10.2018
 */
final class LogQueryBuilderImpl implements LogQuery.Builder {

    static final long MIN_BLOCK = 0;
    static final long MAX_BLOCK = 99999999999999999L;

    private final String address;
    private final long startBlock, endBlock;

    LogQueryBuilderImpl(String address, long startBlock, long endBlock) {
        this.address = address;
        this.startBlock = startBlock;
        this.endBlock = endBlock;
    }

    @Override
    public @NotNull LogQuery.Builder withBlockFrom(long startBlock) {
        return new LogQueryBuilderImpl(this.address, startBlock, this.endBlock);
    }

    @Override
    public @NotNull LogQuery.Builder withBlockTo(long endBlock) {
        return new LogQueryBuilderImpl(this.address, this.startBlock, endBlock);
    }

    @Override
    public @NotNull LogTopicSingle withTopic(String topic0) {
        if (BasicUtils.isNotHex(topic0))
            throw new ErtherScanLogQueryException("topic0 can not be empty or non hex.");
        return new LogTopicSingle(address, startBlock, endBlock, topic0);
    }

    @Override
    public @NotNull LogTopicTuple withTopic(String topic0, String topic1) {
        if (BasicUtils.isNotHex(topic0))
            throw new ErtherScanLogQueryException("topic0 can not be empty or non hex.");
        if (BasicUtils.isNotHex(topic1))
            throw new ErtherScanLogQueryException("topic1 can not be empty or non hex.");
        return new LogTopicTuple(address, startBlock, endBlock, topic0, topic1);
    }

    @Override
    public @NotNull LogTopicTriple withTopic(String topic0, String topic1, String topic2) {
        if (BasicUtils.isNotHex(topic0))
            throw new ErtherScanLogQueryException("topic0 can not be empty or non hex.");
        if (BasicUtils.isNotHex(topic1))
            throw new ErtherScanLogQueryException("topic1 can not be empty or non hex.");
        if (BasicUtils.isNotHex(topic2))
            throw new ErtherScanLogQueryException("topic2 can not be empty or non hex.");
        return new LogTopicTriple(address, startBlock, endBlock, topic0, topic1, topic2);
    }

    @Override
    public @NotNull LogTopicQuadro withTopic(String topic0, String topic1, String topic2, String topic3) {
        if (BasicUtils.isNotHex(topic0))
            throw new ErtherScanLogQueryException("topic0 can not be empty or non hex.");
        if (BasicUtils.isNotHex(topic1))
            throw new ErtherScanLogQueryException("topic1 can not be empty or non hex.");
        if (BasicUtils.isNotHex(topic2))
            throw new ErtherScanLogQueryException("topic2 can not be empty or non hex.");
        if (BasicUtils.isNotHex(topic3))
            throw new ErtherScanLogQueryException("topic3 can not be empty or non hex.");

        return new LogTopicQuadro(address, startBlock, endBlock, topic0, topic1, topic2, topic3);
    }

    @Override
    public @NotNull LogQuery build() throws ErtherScanLogQueryException {
        return new LogQueryImpl("&address=" + this.address + "&fromBlock=" + this.startBlock + "&toBlock=" + this.endBlock);
    }
}
