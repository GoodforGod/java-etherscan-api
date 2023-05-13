package io.goodforgod.api.etherscan.logs;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.model.Log;
import io.goodforgod.api.etherscan.model.query.LogOp;
import io.goodforgod.api.etherscan.model.query.LogQuery;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class LogsApiTest extends ApiRunner {

    static Stream<Arguments> source() {
        LogQuery single = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .build();

        LogQuery singleInvalidAddr = LogQuery.builder("0x13990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .build();

        LogQuery tupleAnd = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c").withBlockFrom(379224)
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .build();

        LogQuery tupleOr = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.OR)
                .build();

        return Stream.of(
                Arguments.of(single, 424),
                Arguments.of(singleInvalidAddr, 0),
                Arguments.of(tupleAnd, 1),
                Arguments.of(tupleOr, 426));
    }

    @ParameterizedTest
    @MethodSource("source")
    void validateQuery(LogQuery query, int logsSize) {
        List<Log> logs = getApi().logs().logs(query);
        assertEquals(logsSize, logs.size());

        if (logsSize > 0) {
            if (logsSize > 1) {
                assertNotEquals(logs.get(0), logs.get(1));
                assertNotEquals(logs.get(0).hashCode(), logs.get(1).hashCode());
            }

            assertNotNull(logs.get(0).getAddress());
            assertNotNull(logs.get(0).getBlockNumber());
            assertNotNull(logs.get(0).getData());
            assertNotNull(logs.get(0).getTimeStamp());
            assertNotNull(logs.get(0).getTransactionHash());
            assertNotNull(logs.get(0).getTransactionIndex());
            assertNotNull(logs.get(0).getGasUsed());
            assertNotNull(logs.get(0).getTopics());
            assertNotNull(logs.get(0).getLogIndex());
            assertNotNull(logs.get(0).getGasPrice());
            assertNotNull(logs.get(0).toString());

            assertEquals(logs.get(0), logs.get(0));
            assertEquals(logs.get(0).hashCode(), logs.get(0).hashCode());
        }
    }
}
