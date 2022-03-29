package io.api.etherscan.logs;

import io.api.ApiRunner;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.error.LogQueryException;
import io.api.etherscan.model.query.LogOp;
import io.api.etherscan.model.query.impl.LogQuery;
import io.api.etherscan.model.query.impl.LogQueryBuilder;
import io.api.etherscan.model.query.impl.LogTopicQuadro;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class LogQueryBuilderTest extends ApiRunner {

    @Test
    void singleCorrect() {
        LogQuery single = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .build();

        assertNotNull(single);
        assertNotNull(single.getParams());
    }

    @Test
    void singleInCorrectAddress() {
        assertThrows(InvalidAddressException.class, () -> LogQueryBuilder.with("033990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .build());
    }

    @Test
    void singleInCorrectTopic() {
        assertThrows(LogQueryException.class, () -> LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("6516=")
                .build());
    }

    @Test
    void tupleCorrect() {
        LogQuery tuple = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .build();

        assertNotNull(tuple);
        assertNotNull(tuple.getParams());
    }

    @Test
    void tupleInCorrectOp() {
        assertThrows(LogQueryException.class, () -> LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224)
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(null)
                .build());
    }

    @Test
    void tripleCorrect() {
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

    @Test
    void tripleInCorrectOp() {
        assertThrows(LogQueryException.class,
                () -> LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                        .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                        .setOpTopic0_1(LogOp.AND)
                        .setOpTopic0_2(null)
                        .setOpTopic1_2(LogOp.AND)
                        .build());
    }

    @Test
    void tripleInCorrectTopic1() {
        assertThrows(LogQueryException.class,
                () -> LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                        .topic(null,
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                        .setOpTopic0_1(LogOp.AND)
                        .setOpTopic0_2(LogOp.AND)
                        .setOpTopic1_2(LogOp.AND)
                        .build());
    }

    @Test
    void tripleInCorrectTopic2() {
        assertThrows(LogQueryException.class,
                () -> LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                        .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                                null,
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                        .setOpTopic0_1(LogOp.AND)
                        .setOpTopic0_2(LogOp.AND)
                        .setOpTopic1_2(LogOp.AND)
                        .build());
    }

    @Test
    void tripleInCorrectTopic3() {
        assertThrows(LogQueryException.class,
                () -> LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                        .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                                null)
                        .setOpTopic0_1(LogOp.AND)
                        .setOpTopic0_2(LogOp.AND)
                        .setOpTopic1_2(LogOp.AND)
                        .build());
    }

    @Test
    void quadroCorrect() {
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

    @Test
    void quadroIncorrectTopic2() {
        assertThrows(LogQueryException.class, () -> LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
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
                .build());
    }

    @Test
    void tupleIncorrectTopic2() {
        assertThrows(LogQueryException.class, () -> LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        null)
                .setOpTopic0_1(LogOp.AND)
                .build());
    }

    @Test
    void tupleIncorrectTopic1() {
        assertThrows(LogQueryException.class, () -> LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic(null,
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .setOpTopic0_1(LogOp.AND)
                .build());
    }

    @Test
    void quadroIncorrectOp1() {
        final LogTopicQuadro topicQuadro = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000");

        assertThrows(LogQueryException.class, () -> topicQuadro
                .setOpTopic0_1(null)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.OR)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build());
    }

    @Test
    void quadroIncorrectOp2() {
        final LogTopicQuadro topicQuadro = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000");

        assertThrows(LogQueryException.class, () -> topicQuadro.setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(null)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.OR)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build());
    }

    @Test
    void quadroIncorrectOp3() {
        final LogTopicQuadro topicQuadro = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000");

        assertThrows(LogQueryException.class, () -> topicQuadro
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(null)
                .setOpTopic1_2(LogOp.OR)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build());
    }

    @Test
    void quadroInCorrectAgainTopic() {
        assertThrows(LogQueryException.class, () -> LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
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
                .build());
    }

    @Test
    void quadroInCorrectOp4() {
        final LogTopicQuadro topicQuadro = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545");

        assertThrows(LogQueryException.class, () -> topicQuadro
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(null)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build());
    }

    @Test
    void quadroInCorrectOp5() {
        final LogTopicQuadro topicQuadro = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545");

        assertThrows(LogQueryException.class, () -> topicQuadro
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.AND)
                .setOpTopic1_3(null)
                .setOpTopic2_3(LogOp.OR)
                .build());
    }

    @Test
    void quadroInCorrectOp6() {
        LogTopicQuadro topicQuadro = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545");

        assertThrows(LogQueryException.class, () -> topicQuadro
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.AND)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(null)
                .build());
    }

    @Test
    void quadroInCorrectTopic() {
        assertThrows(LogQueryException.class, () -> LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
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
                .build());
    }
}
