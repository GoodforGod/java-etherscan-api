package io.goodforgod.api.etherscan.model.query;

import static io.goodforgod.api.etherscan.model.query.LogQueryBuilderImpl.MAX_BLOCK;
import static io.goodforgod.api.etherscan.model.query.LogQueryBuilderImpl.MIN_BLOCK;

import io.goodforgod.api.etherscan.LogsAPI;
import org.jetbrains.annotations.NotNull;

/**
 * Final builded container for The Event Log API
 * EtherScan - API Descriptions <a href="https://etherscan.io/apis#logs">...</a>
 *
 * @see LogQueryBuilderImpl
 * @see LogsAPI
 * @author GoodforGod
 * @since 10.05.2023
 */
public interface LogQuery {

    @NotNull
    String params();

    @NotNull
    static Builder builder(String address) {
        return new LogQueryBuilderImpl(address, MIN_BLOCK, MAX_BLOCK);
    }

    interface Builder {

        @NotNull
        LogQuery.Builder withBlockFrom(long startBlock);

        @NotNull
        LogQuery.Builder withBlockTo(long endBlock);

        @NotNull
        LogTopicSingle withTopic(String topic0);

        @NotNull
        LogTopicTuple withTopic(String topic0, String topic1);

        @NotNull
        LogTopicTriple withTopic(String topic0, String topic1, String topic2);

        @NotNull
        LogTopicQuadro withTopic(String topic0, String topic1, String topic2, String topic3);

        @NotNull
        LogQuery build();
    }
}
