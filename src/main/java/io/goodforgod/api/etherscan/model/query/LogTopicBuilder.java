package io.goodforgod.api.etherscan.model.query;

import org.jetbrains.annotations.NotNull;

/**
 * @see LogTopicSingle
 * @see LogTopicTuple
 * @see LogTopicTriple
 * @see LogTopicQuadro
 * @author GoodforGod
 * @since 10.05.2023
 */
public interface LogTopicBuilder {

    @NotNull
    LogQuery build();
}
