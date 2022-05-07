package io.api.etherscan.logs;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.model.Log;
import io.api.etherscan.model.query.LogOp;
import io.api.etherscan.model.query.LogTopic;
import io.api.etherscan.model.query.impl.LogQuery;
import io.api.etherscan.model.query.impl.LogQueryBuilder;
import io.api.etherscan.model.query.impl.topic.*;
import io.api.etherscan.model.query.impl.topic.operator.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LogApiTest extends Assert {

    public static final String AAVE_ADDRESS = "0x7937d4799803fbbe595ed57278bc4ca21f3bffcb";
    public static final String AAVE_WITHDRAW_HEX = "0x3115d1449a7b732c986cba18244e897a450f61e1bb8d589cd2e69e6c8924f9f7";
    public static final String AAVE_USER_HEX = "0x00000000000000000000000073f984d645eea2fe33737d70ce3d637972fe246f";
    public static final String RESERVE_HEX = "0x000000000000000000000000dac17f958d2ee523a2206206994597c13d831ec7";
    private static final String LOOKS_TOKEN_ADDRESS = "0xf4d2888d29d722226fafa5d9b24f9164c092421e";
    private static final String AGGREGATOR_FEE_SHARING_HEX = "0x0000000000000000000000003ab16af1315dc6c95f83cbf522fecf98d00fd9ba";
    private static final String LOOKS_TOKEN_DISTRIBUTOR_HEX = "0x000000000000000000000000465a790b428268196865a3ae2648481ad7e0d3b1";
    private static final String TOKEN_TRANSFER_HEX = "0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef";
    private static final String RANDOM_ADDRESS_HEX = "0x0000000000000000000000000e011f17b0cfed38c4fa470e74136edbb097df1f";

    @Test
    public void single_topic0() {
        LogQuery single = LogQueryBuilder.with(LOOKS_TOKEN_ADDRESS, 14514697, 14514697)
                .topic(TopicParamsFactory.of(createTopicZero(TOKEN_TRANSFER_HEX)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(single);
        assertNotNull(logs);
        assertEquals(10, logs.size());
        logs.forEach(log -> {
            assertEquals(LOOKS_TOKEN_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(3, topics.size());
            assertEquals(TOKEN_TRANSFER_HEX, topics.get(0));
        });
    }

    @Test
    public void single_topic1() {
        LogQuery single = LogQueryBuilder.with(LOOKS_TOKEN_ADDRESS, 14514697, 14514697)
                .topic(TopicParamsFactory.of(createTopicOne(RANDOM_ADDRESS_HEX)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(single);
        assertNotNull(logs);
        assertEquals(2, logs.size());
        logs.forEach(log -> {
            assertEquals(LOOKS_TOKEN_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(3, topics.size());
            assertEquals(RANDOM_ADDRESS_HEX, topics.get(1));
        });
    }

    @Test
    public void single_topic2() {
        LogQuery single = LogQueryBuilder.with(LOOKS_TOKEN_ADDRESS, 14514697, 14514697)
                .topic(TopicParamsFactory.of(createTopicTwo(LOOKS_TOKEN_DISTRIBUTOR_HEX)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(single);
        assertNotNull(logs);
        assertEquals(5, logs.size());
        logs.forEach(log -> {
            assertEquals(LOOKS_TOKEN_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(3, topics.size());
            assertEquals(LOOKS_TOKEN_DISTRIBUTOR_HEX, topics.get(2));
        });
    }

    @Test
    public void single_topic3() {
        LogQuery single = LogQueryBuilder.with(AAVE_ADDRESS, 14514529, 14514529)
                .topic(TopicParamsFactory.of(createTopicThree(AAVE_USER_HEX)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(single);
        assertNotNull(logs);
        assertEquals(1, logs.size());
        logs.forEach(log -> {
            assertEquals(AAVE_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(4, topics.size());
            assertEquals(AAVE_USER_HEX, topics.get(3));
        });
    }

    @Test
    public void pair_topic0And1() {
        LogQuery pair = LogQueryBuilder.with(LOOKS_TOKEN_ADDRESS, 14514697, 14514697)
                .topic(TopicParamsFactory.of(
                        createTopicZero(TOKEN_TRANSFER_HEX),
                        createTopicOne(RANDOM_ADDRESS_HEX),
                        new LogOperatorZeroOne(LogOp.AND)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(pair);
        assertNotNull(logs);
        assertEquals(1, logs.size());
        logs.forEach(log -> {
            assertEquals(LOOKS_TOKEN_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(3, topics.size());
            assertEquals(TOKEN_TRANSFER_HEX, topics.get(0));
            assertEquals(RANDOM_ADDRESS_HEX, topics.get(1));
        });
    }

    @Test
    public void pair_topic0And2() {
        LogQuery pair = LogQueryBuilder.with(LOOKS_TOKEN_ADDRESS, 14514697, 14514697)
                .topic(TopicParamsFactory.of(
                        createTopicZero(TOKEN_TRANSFER_HEX),
                        createTopicTwo(LOOKS_TOKEN_DISTRIBUTOR_HEX),
                        new LogOperatorZeroTwo(LogOp.AND)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(pair);
        assertNotNull(logs);
        assertEquals(3, logs.size());
        logs.forEach(log -> {
            assertEquals(LOOKS_TOKEN_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(3, topics.size());
            assertEquals(TOKEN_TRANSFER_HEX, topics.get(0));
            assertEquals(LOOKS_TOKEN_DISTRIBUTOR_HEX, topics.get(2));
        });
    }

    @Test
    public void pair_topic0And3() {
        LogQuery pair = LogQueryBuilder.with(AAVE_ADDRESS, 14514529, 14514529)
                .topic(TopicParamsFactory.of(createTopicZero(AAVE_WITHDRAW_HEX),
                        createTopicThree(AAVE_USER_HEX),
                        new LogOperatorZeroThree(LogOp.AND)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(pair);
        assertNotNull(logs);
        assertEquals(1, logs.size());
        logs.forEach(log -> {
            assertEquals(AAVE_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(4, topics.size());
            assertEquals(AAVE_WITHDRAW_HEX, topics.get(0));
            assertEquals(AAVE_USER_HEX, topics.get(3));
        });
    }

    @Test
    public void pair_topic1And3() {
        LogQuery pair = LogQueryBuilder.with(AAVE_ADDRESS, 14514529, 14514529)
                .topic(TopicParamsFactory.of(createTopicOne(RESERVE_HEX),
                        createTopicThree(AAVE_USER_HEX),
                        new LogOperatorOneThree(LogOp.AND)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(pair);
        assertNotNull(logs);
        assertEquals(1, logs.size());
        logs.forEach(log -> {
            assertEquals(AAVE_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(4, topics.size());
            assertEquals(RESERVE_HEX, topics.get(1));
            assertEquals(AAVE_USER_HEX, topics.get(2));
            assertEquals(AAVE_USER_HEX, topics.get(3));
        });
    }

    @Test
    public void pair_topic2And3() {
        LogQuery quadruple = LogQueryBuilder.with(AAVE_ADDRESS, 14514529, 14514529)
                .topic(TopicParamsFactory.of(createTopicTwo(AAVE_USER_HEX),
                        createTopicThree(AAVE_USER_HEX),
                        new LogOperatorTwoThree(LogOp.AND)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(quadruple);
        assertNotNull(logs);
        assertEquals(1, logs.size());
        logs.forEach(log -> {
            assertEquals(AAVE_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(4, topics.size());
            assertEquals(AAVE_USER_HEX, topics.get(2));
            assertEquals(AAVE_USER_HEX, topics.get(3));
        });
    }

    @Test
    public void triple_topic0And1And2() {
        LogQuery triple = LogQueryBuilder.with(LOOKS_TOKEN_ADDRESS, 14514697, 14514697)
                .topic(TopicParamsFactory.of(
                        createTopicZero(TOKEN_TRANSFER_HEX),
                        createTopicOne(RANDOM_ADDRESS_HEX),
                        createTopicTwo(AGGREGATOR_FEE_SHARING_HEX),
                        new LogOperatorZeroOne(LogOp.AND),
                        new LogOperatorZeroTwo(LogOp.AND),
                        new LogOperatorOneTwo(LogOp.AND)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(triple);
        assertNotNull(logs);
        assertEquals(1, logs.size());
        logs.forEach(log -> {
            assertEquals(LOOKS_TOKEN_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(3, topics.size());
            assertEquals(TOKEN_TRANSFER_HEX, topics.get(0));
            assertEquals(RANDOM_ADDRESS_HEX, topics.get(1));
            assertEquals(AGGREGATOR_FEE_SHARING_HEX, topics.get(2));
        });
    }

    @Test
    public void triple_topic0And1And3() {
        LogQuery triple = LogQueryBuilder.with(AAVE_ADDRESS, 14514529, 14514529)
                .topic(TopicParamsFactory.of(createTopicZero(AAVE_WITHDRAW_HEX),
                        createTopicOne(RESERVE_HEX),
                        createTopicThree(AAVE_USER_HEX),
                        new LogOperatorZeroOne(LogOp.AND),
                        new LogOperatorZeroThree(LogOp.AND),
                        new LogOperatorOneThree(LogOp.AND)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(triple);
        assertNotNull(logs);
        assertEquals(1, logs.size());
        logs.forEach(log -> {
            assertEquals(AAVE_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(4, topics.size());
            assertEquals(AAVE_WITHDRAW_HEX, topics.get(0));
            assertEquals(RESERVE_HEX, topics.get(1));
            assertEquals(AAVE_USER_HEX, topics.get(3));
        });
    }

    @Test
    public void triple_topic1And2And3() {
        LogQuery triple = LogQueryBuilder.with(AAVE_ADDRESS, 14514529, 14514529)
                .topic(TopicParamsFactory.of(createTopicOne(RESERVE_HEX),
                        createTopicTwo(AAVE_USER_HEX),
                        createTopicThree(AAVE_USER_HEX),
                        new LogOperatorOneTwo(LogOp.AND),
                        new LogOperatorOneThree(LogOp.AND),
                        new LogOperatorTwoThree(LogOp.AND)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(triple);
        assertNotNull(logs);
        assertEquals(1, logs.size());
        logs.forEach(log -> {
            assertEquals(AAVE_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(4, topics.size());
            assertEquals(RESERVE_HEX, topics.get(1));
            assertEquals(AAVE_USER_HEX, topics.get(2));
            assertEquals(AAVE_USER_HEX, topics.get(3));
        });
    }

    @Test
    public void quadruple() {
        LogQuery quadruple = LogQueryBuilder.with(AAVE_ADDRESS, 14514529, 14514529)
                .topic(TopicParamsFactory.of(createTopicZero(AAVE_WITHDRAW_HEX),
                        createTopicOne(RESERVE_HEX),
                        createTopicTwo(AAVE_USER_HEX),
                        createTopicThree(AAVE_USER_HEX),
                        new LogOperatorZeroOne(LogOp.AND),
                        new LogOperatorZeroTwo(LogOp.AND),
                        new LogOperatorZeroThree(LogOp.AND),
                        new LogOperatorOneTwo(LogOp.AND),
                        new LogOperatorOneThree(LogOp.AND),
                        new LogOperatorTwoThree(LogOp.AND)))
                .build();

        List<Log> logs = new EtherScanApi().logs().logs(quadruple);
        assertNotNull(logs);
        assertEquals(1, logs.size());
        logs.forEach(log -> {
            assertEquals(AAVE_ADDRESS, log.getAddress());
            List<String> topics = log.getTopics();
            assertEquals(4, topics.size());
            assertEquals(AAVE_WITHDRAW_HEX, topics.get(0));
            assertEquals(RESERVE_HEX, topics.get(1));
            assertEquals(AAVE_USER_HEX, topics.get(2));
            assertEquals(AAVE_USER_HEX, topics.get(3));
        });
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
