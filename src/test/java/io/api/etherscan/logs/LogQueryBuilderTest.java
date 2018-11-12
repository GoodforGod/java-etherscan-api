package io.api.etherscan.logs;

import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.error.LogQueryException;
import io.api.etherscan.model.query.LogOp;
import io.api.etherscan.model.query.impl.LogQuery;
import io.api.etherscan.model.query.impl.LogQueryBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class LogQueryBuilderTest extends Assert {

    @Test
    public void singleCorrect() {
        LogQuery single = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
            .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
            .build();

        assertNotNull(single);
        assertNotNull(single.getParams());
    }

    @Test(expected = InvalidAddressException.class)
    public void singleInCorrectAddress() {
        LogQuery single = LogQueryBuilder.with("033990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .build();

        assertNotNull(single);
        assertNotNull(single.getParams());
    }

    @Test(expected = LogQueryException.class)
    public void singleInCorrectTopic() {
        LogQuery single = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("6516=")
                .build();

        assertNotNull(single);
        assertNotNull(single.getParams());
    }

    @Test
    public void tupleCorrect() {
        LogQuery tuple = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .build();

        assertNotNull(tuple);
        assertNotNull(tuple.getParams());
    }

    @Test(expected = LogQueryException.class)
    public void tupleInCorrectOp() {
        LogQuery tuple = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(null)
                .build();

        assertNotNull(tuple);
        assertNotNull(tuple.getParams());
    }

    @Test
    public void tripleCorrect() {
        LogQuery triple = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic1_2(LogOp.AND)
                .build();

        assertNotNull(triple);
        assertNotNull(triple.getParams());
    }

    @Test(expected = LogQueryException.class)
    public void tripleInCorrectOp() {
        LogQuery triple = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(null)
                .setOpTopic1_2(LogOp.AND)
                .build();

        assertNotNull(triple);
        assertNotNull(triple.getParams());
    }

    @Test
    public void quadroCorrect() {
        LogQuery quadro = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.OR)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build();

        assertNotNull(quadro);
        assertNotNull(quadro.getParams());
    }

    @Test(expected = LogQueryException.class)
    public void quadroIncorrectTopic() {
        LogQuery quadro = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        null,
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.OR)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build();

        assertNotNull(quadro);
        assertNotNull(quadro.getParams());
    }


    @Test(expected = LogQueryException.class)
    public void quadroInCorrectAgainTopic() {
        LogQuery quadro = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        null)
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.OR)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build();

        assertNotNull(quadro);
        assertNotNull(quadro.getParams());
    }

    @Test(expected = LogQueryException.class)
    public void quadroInCorrectOp() {
        LogQuery quadro = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "",
                        "")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(null)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build();

        assertNotNull(quadro);
        assertNotNull(quadro.getParams());
    }

    @Test(expected = LogQueryException.class)
    public void quadroInCorrectTopic() {
        LogQuery quadro = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "",
                        "")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.OR)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build();

        assertNotNull(quadro);
        assertNotNull(quadro.getParams());
    }
}
