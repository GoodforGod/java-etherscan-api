package io.api.etherscan.logs;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.model.Log;
import io.api.etherscan.model.query.LogOp;
import io.api.etherscan.model.query.impl.LogQuery;
import io.api.etherscan.model.query.impl.LogQueryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
@RunWith(Parameterized.class)
public class LogsApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    private LogQuery query;
    private int logsSize;

    public LogsApiTest(LogQuery query, int logsSize) {
        this.query = query;
        this.logsSize = logsSize;
    }

    @Parameters
    public static Collection data() {
        LogQuery single = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .build();

        LogQuery singleInvalidAddr = LogQueryBuilder.with("0x13990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .build();

        LogQuery tupleAnd = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .build();

        LogQuery tupleOr = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.OR)
                .build();

        return Arrays.asList(new Object[][]{
                {single, 423},
                {singleInvalidAddr, 0},
                {tupleAnd, 1},
                {tupleOr, 425}
        });
    }

    @Test
    public void validateQuery() {
        List<Log> logs = api.logs().logs(query);
        assertEquals(logsSize, logs.size());

        if (logsSize > 0) {
            if(logsSize > 1) {
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
