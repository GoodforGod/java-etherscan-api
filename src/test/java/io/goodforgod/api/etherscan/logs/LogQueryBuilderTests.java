package io.goodforgod.api.etherscan.logs;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.error.ErtherScanLogQueryException;
import io.goodforgod.api.etherscan.error.EtherScanInvalidAddressException;
import io.goodforgod.api.etherscan.model.query.*;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class LogQueryBuilderTests extends ApiRunner {

    @Test
    void singleCorrect() {
        LogQuery single = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .build();

        assertNotNull(single);
        assertNotNull(single.params());
    }

    @Test
    void singleInCorrectAddress() {
        assertThrows(EtherScanInvalidAddressException.class,
                () -> LogQuery.builder("033990122638b9132ca29c723bdf037f1a891a70c")
                        .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                        .build());
    }

    @Test
    void singleInCorrectTopic() {
        assertThrows(ErtherScanLogQueryException.class, () -> LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("6516=")
                .build());
    }

    @Test
    void tupleCorrect() {
        LogQuery tuple = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c").withBlockFrom(379224)
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .build();

        assertNotNull(tuple);
        assertNotNull(tuple.params());
    }

    @Test
    void tupleInCorrectOp() {
        assertThrows(ErtherScanLogQueryException.class,
                () -> LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c").withBlockFrom(379224)
                        .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                        .setOpTopic0_1(null)
                        .build());
    }

    @Test
    void tripleCorrect() {
        LogQuery triple = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c").withBlockFrom(379224).withBlockTo(400000)
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                .setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(LogOp.OR)
                .setOpTopic1_2(LogOp.AND)
                .build();

        assertNotNull(triple);
        assertNotNull(triple.params());
    }

    @Test
    void tripleInCorrectOp() {
        assertThrows(ErtherScanLogQueryException.class,
                () -> LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c").withBlockFrom(379224).withBlockTo(400000)
                        .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                        .setOpTopic0_1(LogOp.AND)
                        .setOpTopic0_2(null)
                        .setOpTopic1_2(LogOp.AND)
                        .build());
    }

    @Test
    void tripleInCorrectTopic1() {
        assertThrows(ErtherScanLogQueryException.class,
                () -> LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c").withBlockFrom(379224).withBlockTo(400000)
                        .withTopic(null,
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                        .setOpTopic0_1(LogOp.AND)
                        .setOpTopic0_2(LogOp.AND)
                        .setOpTopic1_2(LogOp.AND)
                        .build());
    }

    @Test
    void tripleInCorrectTopic2() {
        assertThrows(ErtherScanLogQueryException.class,
                () -> LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c").withBlockFrom(379224).withBlockTo(400000)
                        .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                                null,
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000")
                        .setOpTopic0_1(LogOp.AND)
                        .setOpTopic0_2(LogOp.AND)
                        .setOpTopic1_2(LogOp.AND)
                        .build());
    }

    @Test
    void tripleInCorrectTopic3() {
        assertThrows(ErtherScanLogQueryException.class,
                () -> LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c").withBlockFrom(379224).withBlockTo(400000)
                        .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                                "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                                null)
                        .setOpTopic0_1(LogOp.AND)
                        .setOpTopic0_2(LogOp.AND)
                        .setOpTopic1_2(LogOp.AND)
                        .build());
    }

    @Test
    void quadroCorrect() {
        LogQuery quadro = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
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
        assertNotNull(quadro.params());
    }

    @Test
    void quadroIncorrectTopic2() {
        assertThrows(ErtherScanLogQueryException.class, () -> LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
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
        assertThrows(ErtherScanLogQueryException.class, () -> LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        null)
                .setOpTopic0_1(LogOp.AND)
                .build());
    }

    @Test
    void tupleIncorrectTopic1() {
        assertThrows(ErtherScanLogQueryException.class, () -> LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic(null,
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
                .setOpTopic0_1(LogOp.AND)
                .build());
    }

    @Test
    void quadroIncorrectOp1() {
        final LogTopicQuadro topicQuadro = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000");

        assertThrows(ErtherScanLogQueryException.class, () -> topicQuadro
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
        final LogTopicQuadro topicQuadro = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000");

        assertThrows(ErtherScanLogQueryException.class, () -> topicQuadro.setOpTopic0_1(LogOp.AND)
                .setOpTopic0_2(null)
                .setOpTopic0_3(LogOp.AND)
                .setOpTopic1_2(LogOp.OR)
                .setOpTopic1_3(LogOp.OR)
                .setOpTopic2_3(LogOp.OR)
                .build());
    }

    @Test
    void quadroIncorrectOp3() {
        final LogTopicQuadro topicQuadro = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000");

        assertThrows(ErtherScanLogQueryException.class, () -> topicQuadro
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
        assertThrows(ErtherScanLogQueryException.class, () -> LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
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
        final LogTopicQuadro topicQuadro = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545");

        assertThrows(ErtherScanLogQueryException.class, () -> topicQuadro
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
        final LogTopicQuadro topicQuadro = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545");

        assertThrows(ErtherScanLogQueryException.class, () -> topicQuadro
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
        LogTopicQuadro topicQuadro = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                        "0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545");

        assertThrows(ErtherScanLogQueryException.class, () -> topicQuadro
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
        assertThrows(ErtherScanLogQueryException.class, () -> LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
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
