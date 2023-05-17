package io.goodforgod.api.etherscan.model.query;

import static io.goodforgod.api.etherscan.model.query.LogQueryBuilderImpl.MAX_BLOCK;
import static io.goodforgod.api.etherscan.model.query.LogQueryBuilderImpl.MIN_BLOCK;

import io.goodforgod.api.etherscan.LogsAPI;
import org.jetbrains.annotations.NotNull;

/**
 * Final built container for The Event Log API
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
    static Builder builder(@NotNull String address) {
        return new LogQueryBuilderImpl(address, MIN_BLOCK, MAX_BLOCK);
    }

    interface Builder {

        @NotNull
        LogQuery.Builder withBlockFrom(long startBlock);

        @NotNull
        LogQuery.Builder withBlockTo(long endBlock);

        @NotNull
        LogTopicSingle withTopic(@NotNull String topic0);

        @NotNull
        LogTopicTuple withTopic(@NotNull String topic0, @NotNull String topic1);

        @NotNull
        LogTopicTriple withTopic(@NotNull String topic0, @NotNull String topic1, @NotNull String topic2);

        @NotNull
        LogTopicQuadro withTopic(@NotNull String topic0, @NotNull String topic1, @NotNull String topic2, @NotNull String topic3);

        @NotNull
        LogQuery build();
    }
}
