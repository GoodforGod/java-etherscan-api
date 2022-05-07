package io.api.etherscan.logs;

import io.api.ApiRunner;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.query.LogOp;
import io.api.etherscan.model.query.LogTopic;
import io.api.etherscan.model.query.impl.LogQuery;
import io.api.etherscan.model.query.impl.LogQueryBuilder;
import io.api.etherscan.model.query.impl.topic.*;
import io.api.etherscan.model.query.impl.topic.operator.*;
import org.junit.Test;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class LogQueryBuilderTest extends ApiRunner {

    @Test(expected = InvalidAddressException.class)
    public void inCorrectAddress() {
        LogQueryBuilder.with("033990122638b9132ca29c723bdf037f1a891a70c")
                .build();
    }

    @Test
    public void standard() {
        LogQuery standard = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 69L, 420L)
                .build();

        assertNotNull(standard);
        assertNotNull(standard.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=69"
                + "&toBlock=420", standard.getParams());
    }

    @Test
    public void single_topic0() {
        LogQuery single = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic(TopicParamsFactory.of(createTopicZero("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")))
                .build();

        assertNotNull(single);
        assertNotNull(single.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=0"
                + "&toBlock=99999999999"
                + "&topic0=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545", single.getParams());
    }

    @Test
    public void single_topic1() {
        LogQuery single = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic(TopicParamsFactory.of(createTopicOne("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")))
                .build();

        assertNotNull(single);
        assertNotNull(single.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=0"
                + "&toBlock=99999999999"
                + "&topic1=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545", single.getParams());
    }

    @Test
    public void single_topic2() {
        LogQuery single = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic(TopicParamsFactory.of(createTopicTwo("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")))
                .build();

        assertNotNull(single);
        assertNotNull(single.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=0"
                + "&toBlock=99999999999"
                + "&topic2=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545", single.getParams());
    }

    @Test
    public void single_topic3() {
        LogQuery single = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
                .topic(TopicParamsFactory.of(createTopicThree("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")))
                .build();

        assertNotNull(single);
        assertNotNull(single.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=0"
                + "&toBlock=99999999999"
                + "&topic3=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545", single.getParams());
    }

    @Test
    public void pair_zeroOne() {
        LogQuery pair = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224)
                .topic(TopicParamsFactory.of(
                        createTopicZero("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"),
                        createTopicOne("0x72657075746174696f6e00000000000000000000000000000000000000000000"),
                        new LogOperatorZeroOne(LogOp.AND)))
                .build();

        assertNotNull(pair);
        assertNotNull(pair.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=379224"
                + "&toBlock=99999999999"
                + "&topic0=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"
                + "&topic1=0x72657075746174696f6e00000000000000000000000000000000000000000000"
                + "&topic0_1_opr=and", pair.getParams());
    }

    @Test
    public void pair_zeroTwo() {
        LogQuery pair = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224)
                .topic(TopicParamsFactory.of(
                        createTopicZero("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"),
                        createTopicTwo("0x72657075746174696f6e00000000000000000000000000000000000000000000"),
                        new LogOperatorZeroTwo(LogOp.OR)))
                .build();

        assertNotNull(pair);
        assertNotNull(pair.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=379224"
                + "&toBlock=99999999999"
                + "&topic0=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"
                + "&topic2=0x72657075746174696f6e00000000000000000000000000000000000000000000"
                + "&topic0_2_opr=or", pair.getParams());
    }

    @Test
    public void pair_zeroThree() {
        LogQuery pair = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224)
                .topic(TopicParamsFactory.of(
                        createTopicZero("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"),
                        createTopicThree("0x72657075746174696f6e00000000000000000000000000000000000000000000"),
                        new LogOperatorZeroThree(LogOp.OR)))
                .build();

        assertNotNull(pair);
        assertNotNull(pair.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=379224"
                + "&toBlock=99999999999"
                + "&topic0=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"
                + "&topic3=0x72657075746174696f6e00000000000000000000000000000000000000000000"
                + "&topic0_3_opr=or", pair.getParams());
    }

    @Test
    public void pair_oneTwo() {
        LogQuery pair = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224)
                .topic(TopicParamsFactory.of(
                        createTopicOne("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"),
                        createTopicTwo("0x72657075746174696f6e00000000000000000000000000000000000000000000"),
                        new LogOperatorOneTwo(LogOp.AND)))
                .build();

        assertNotNull(pair);
        assertNotNull(pair.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=379224"
                + "&toBlock=99999999999"
                + "&topic1=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"
                + "&topic2=0x72657075746174696f6e00000000000000000000000000000000000000000000"
                + "&topic1_2_opr=and", pair.getParams());
    }

    @Test
    public void pair_oneThree() {
        LogQuery pair = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224)
                .topic(TopicParamsFactory.of(
                        createTopicOne("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"),
                        createTopicThree("0x72657075746174696f6e00000000000000000000000000000000000000000000"),
                        new LogOperatorOneThree(LogOp.AND)))
                .build();

        assertNotNull(pair);
        assertNotNull(pair.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=379224"
                + "&toBlock=99999999999"
                + "&topic1=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"
                + "&topic3=0x72657075746174696f6e00000000000000000000000000000000000000000000"
                + "&topic1_3_opr=and", pair.getParams());
    }

    @Test
    public void pair_twoThree() {
        LogQuery pair = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224)
                .topic(TopicParamsFactory.of(
                        createTopicTwo("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"),
                        createTopicThree("0x72657075746174696f6e00000000000000000000000000000000000000000000"),
                        new LogOperatorTwoThree(LogOp.AND)))
                .build();

        assertNotNull(pair);
        assertNotNull(pair.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=379224"
                + "&toBlock=99999999999"
                + "&topic2=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"
                + "&topic3=0x72657075746174696f6e00000000000000000000000000000000000000000000"
                + "&topic2_3_opr=and", pair.getParams());
    }

    @Test
    public void triple_zeroOneTwo() {
        LogQuery triple = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic(TopicParamsFactory.of(
                        createTopicZero("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"),
                        createTopicOne("0x72657075746174696f6e00000000000000000000000000000000000000000000"),
                        createTopicTwo("0x00000000000000000000000013990122638b9132ca29c723bdf037f1a891a70c"),
                        new LogOperatorZeroOne(LogOp.AND),
                        new LogOperatorZeroTwo(LogOp.OR),
                        new LogOperatorOneTwo(LogOp.OR)))
                .build();

        assertNotNull(triple);
        assertNotNull(triple.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=379224"
                + "&toBlock=400000"
                + "&topic0=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"
                + "&topic1=0x72657075746174696f6e00000000000000000000000000000000000000000000"
                + "&topic2=0x00000000000000000000000013990122638b9132ca29c723bdf037f1a891a70c"
                + "&topic0_1_opr=and"
                + "&topic0_2_opr=or"
                + "&topic1_2_opr=or", triple.getParams());
    }

    @Test
    public void triple_zeroOneThree() {
        LogQuery triple = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic(TopicParamsFactory.of(
                        createTopicZero("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"),
                        createTopicOne("0x72657075746174696f6e00000000000000000000000000000000000000000000"),
                        createTopicThree("0x00000000000000000000000013990122638b9132ca29c723bdf037f1a891a70c"),
                        new LogOperatorZeroOne(LogOp.AND),
                        new LogOperatorZeroThree(LogOp.OR),
                        new LogOperatorOneThree(LogOp.OR)))
                .build();

        assertNotNull(triple);
        assertNotNull(triple.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=379224"
                + "&toBlock=400000"
                + "&topic0=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"
                + "&topic1=0x72657075746174696f6e00000000000000000000000000000000000000000000"
                + "&topic3=0x00000000000000000000000013990122638b9132ca29c723bdf037f1a891a70c"
                + "&topic0_1_opr=and"
                + "&topic0_3_opr=or"
                + "&topic1_3_opr=or", triple.getParams());
    }

    @Test
    public void triple_zeroTwoThree() {
        LogQuery triple = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic(TopicParamsFactory.of(
                        createTopicZero("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"),
                        createTopicTwo("0x72657075746174696f6e00000000000000000000000000000000000000000000"),
                        createTopicThree("0x00000000000000000000000013990122638b9132ca29c723bdf037f1a891a70c"),
                        new LogOperatorZeroTwo(LogOp.AND),
                        new LogOperatorZeroThree(LogOp.OR),
                        new LogOperatorTwoThree(LogOp.OR)))
                .build();

        assertNotNull(triple);
        assertNotNull(triple.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=379224"
                + "&toBlock=400000"
                + "&topic0=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"
                + "&topic2=0x72657075746174696f6e00000000000000000000000000000000000000000000"
                + "&topic3=0x00000000000000000000000013990122638b9132ca29c723bdf037f1a891a70c"
                + "&topic0_2_opr=and"
                + "&topic0_3_opr=or"
                + "&topic2_3_opr=or", triple.getParams());
    }

    @Test
    public void triple_oneTwoThree() {
        LogQuery triple = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic(TopicParamsFactory.of(
                        createTopicOne("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"),
                        createTopicTwo("0x72657075746174696f6e00000000000000000000000000000000000000000000"),
                        createTopicThree("0x00000000000000000000000013990122638b9132ca29c723bdf037f1a891a70c"),
                        new LogOperatorOneTwo(LogOp.AND),
                        new LogOperatorOneThree(LogOp.OR),
                        new LogOperatorTwoThree(LogOp.OR)))
                .build();

        assertNotNull(triple);
        assertNotNull(triple.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=379224"
                + "&toBlock=400000"
                + "&topic1=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"
                + "&topic2=0x72657075746174696f6e00000000000000000000000000000000000000000000"
                + "&topic3=0x00000000000000000000000013990122638b9132ca29c723bdf037f1a891a70c"
                + "&topic1_2_opr=and"
                + "&topic1_3_opr=or"
                + "&topic2_3_opr=or", triple.getParams());
    }

    @Test
    public void quadruple() {
        LogQuery quadruple = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
                .topic(TopicParamsFactory.of(
                        createTopicZero("0x0000000000000000000000001d4426f94e42f721C7116E81d6688cd935cB3b4F"),
                        createTopicOne("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"),
                        createTopicTwo("0x72657075746174696f6e00000000000000000000000000000000000000000000"),
                        createTopicThree("0x00000000000000000000000013990122638b9132ca29c723bdf037f1a891a70c"),
                        new LogOperatorZeroOne(LogOp.OR),
                        new LogOperatorZeroTwo(LogOp.AND),
                        new LogOperatorZeroThree(LogOp.OR),
                        new LogOperatorOneTwo(LogOp.AND),
                        new LogOperatorOneThree(LogOp.OR),
                        new LogOperatorTwoThree(LogOp.OR)))
                .build();

        assertNotNull(quadruple);
        assertNotNull(quadruple.getParams());
        assertEquals("&address=0x33990122638b9132ca29c723bdf037f1a891a70c"
                + "&fromBlock=379224"
                + "&toBlock=400000"
                + "&topic0=0x0000000000000000000000001d4426f94e42f721C7116E81d6688cd935cB3b4F"
                + "&topic1=0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545"
                + "&topic2=0x72657075746174696f6e00000000000000000000000000000000000000000000"
                + "&topic3=0x00000000000000000000000013990122638b9132ca29c723bdf037f1a891a70c"
                + "&topic0_1_opr=or"
                + "&topic0_2_opr=and"
                + "&topic0_3_opr=or"
                + "&topic1_2_opr=and"
                + "&topic1_3_opr=or"
                + "&topic2_3_opr=or", quadruple.getParams());
    }

    private TopicZero createTopicZero(String hex) {
        return new TopicZero(LogTopic.of(hex));
    }

    private TopicOne createTopicOne(String hex) {
        return new TopicOne(LogTopic.of(hex));
    }

    private TopicTwo createTopicTwo(String hex) {
        return new TopicTwo(LogTopic.of(hex));
    }

    private TopicThree createTopicThree(String hex) {
        return new TopicThree(LogTopic.of(hex));
    }
}
