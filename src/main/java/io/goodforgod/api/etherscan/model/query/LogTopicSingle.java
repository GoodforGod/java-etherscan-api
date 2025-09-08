package io.goodforgod.api.etherscan.model.query;

import static io.goodforgod.api.etherscan.model.query.LogQueryParams.*;

import io.goodforgod.api.etherscan.LogsAPI;
import io.goodforgod.api.etherscan.error.EtherScanLogQueryException;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Single topic parameter builder for The Event Log API
 *
 * @see LogQueryBuilderImpl
 * @see LogsAPI
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogTopicSingle implements LogTopicBuilder {

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LogTopicSingle that = (LogTopicSingle) o;
        return startBlock == that.startBlock && endBlock == that.endBlock && Objects.equals(address, that.address)
                && Objects.equals(topic0, that.topic0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, startBlock, endBlock, topic0);
    }

    @Override
    public String toString() {
        return "LogTopicSingle{" +
                "address=" + address + '\'' +
                ", startBlock=" + startBlock +
                ", endBlock=" + endBlock +
                ", topic0=" + topic0 + '\'' +
                '}';
    }
}
