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
        LogQueryBuilder.with("033990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void singleInCorrectTopic() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("6516=")
                .build();
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
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(null)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void tupleIncorrectTopic2() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        null)
                .setOpTopic0_1(LogOp.AND)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void tupleIncorrectTopic1() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic(null,
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .setOpTopic0_1(LogOp.AND)
                .build();
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
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(null)
                .setOpTopic1_2(LogOp.AND)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void tripleInCorrectTopic1() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic(null,
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(null)
                .setOpTopic1_2(LogOp.AND)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void tripleInCorrectTopic2() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        null,
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(null)
                .setOpTopic1_2(LogOp.AND)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void tripleInCorrectTopic3() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        null)
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(null)
                .setOpTopic1_2(LogOp.AND)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void tripleInCorrectOp1() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(null)
                .setOpTopic0_2(LogOp.AND)
                .setOpTopic1_2(LogOp.AND)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void tripleInCorrectOp2() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(null)
                .setOpTopic1_2(LogOp.AND)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void tripleInCorrectOp3() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.AND)
                .setOpTopic1_2(null)
                .build();
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
    public void quadroIncorrectTopic2() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
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
    }

    @Test(expected = LogQueryException.class)
    public void quadroIncorrectTopic1() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic(null,
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.OR)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void quadroIncorrectOp1() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(null)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.OR)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void quadroIncorrectOp2() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(null)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.OR)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void quadroIncorrectOp3() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(null)
                .setOpTopic1_2(LogOp.OR)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void quadroInCorrectAgainTopic() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
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
    }

    @Test(expected = LogQueryException.class)
    public void quadroInCorrectOp4() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(null)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void quadroInCorrectOp5() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.AND)
                .setOpTopic1_3(null)
                .setOpTopic2_3(LogOp.OR)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void quadroInCorrectOp6() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.AND)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(null)
                .build();
    }

    @Test(expected = LogQueryException.class)
    public void quadroInCorrectTopic() {
        LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
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
    }
}
