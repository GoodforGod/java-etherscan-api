package io.api.etherscan.model.query.impl.topic;

import io.api.etherscan.model.query.impl.topic.operator.*;
import io.api.etherscan.model.query.impl.topic.pair.*;
import io.api.etherscan.model.query.impl.topic.quadruple.TopicQuadruple;
import io.api.etherscan.model.query.impl.topic.single.SingleTopic;
import io.api.etherscan.model.query.impl.topic.triple.TopicTripleOneTwoThree;
import io.api.etherscan.model.query.impl.topic.triple.TopicTripleZeroOneThree;
import io.api.etherscan.model.query.impl.topic.triple.TopicTripleZeroOneTwo;
import io.api.etherscan.model.query.impl.topic.triple.TopicTripleZeroTwoThree;

public class TopicParamsFactory {

    /**
     * Factory Method for single Topic
     */

    public static TopicParams of(BaseTopic topic) {
        return new SingleTopic(topic);
    }

    /**
     * Factory Methods for TopicPairs
     */

    public static TopicParams of(TopicZero topicZero, TopicOne topicOne, LogOperatorZeroOne operator) {
        return new TopicPairZeroOne(topicZero, topicOne, operator);
    }

    public static TopicParams of(TopicZero topicZero, TopicTwo topicTwo, LogOperatorZeroTwo operator) {
        return new TopicPairZeroTwo(topicZero, topicTwo, operator);
    }

    public static TopicParams of(TopicZero topicZero, TopicThree topicThree, LogOperatorZeroThree operator) {
        return new TopicPairZeroThree(topicZero, topicThree, operator);
    }

    public static TopicParams of(TopicOne topicOne, TopicTwo topicTwo, LogOperatorOneTwo operator) {
        return new TopicPairOneTwo(topicOne, topicTwo, operator);
    }

    public static TopicParams of(TopicOne topicOne, TopicThree topicThree, LogOperatorOneThree operator) {
        return new TopicPairOneThree(topicOne, topicThree, operator);
    }

    public static TopicParams of(TopicTwo topicTwo, TopicThree topicThree, LogOperatorTwoThree operator) {
        return new TopicPairTwoThree(topicTwo, topicThree, operator);
    }

    /**
     * Factory Methods for TopicTriples
     */

    public static TopicParams of(TopicZero topicZero,
                                 TopicOne topicOne,
                                 TopicTwo topicTwo,
                                 LogOperatorZeroOne operatorZeroOne,
                                 LogOperatorZeroTwo operatorZeroTwo,
                                 LogOperatorOneTwo operatorOneTwo) {
        return new TopicTripleZeroOneTwo(topicZero, topicOne, topicTwo, operatorZeroOne, operatorZeroTwo, operatorOneTwo);
    }

    public static TopicParams of(TopicZero topicZero,
                                 TopicOne topicOne,
                                 TopicThree topicThree,
                                 LogOperatorZeroOne operatorZeroOne,
                                 LogOperatorZeroThree operatorZeroThree,
                                 LogOperatorOneThree operatorOneThree) {
        return new TopicTripleZeroOneThree(topicZero, topicOne, topicThree, operatorZeroOne, operatorZeroThree, operatorOneThree);
    }

    public static TopicParams of(TopicZero topicZero,
                                 TopicTwo topicTwo,
                                 TopicThree topicThree,
                                 LogOperatorZeroTwo operatorZeroTwo,
                                 LogOperatorZeroThree operatorZeroThree,
                                 LogOperatorTwoThree operatorTwoThree) {
        return new TopicTripleZeroTwoThree(topicZero, topicTwo, topicThree, operatorZeroTwo, operatorZeroThree, operatorTwoThree);
    }

    public static TopicParams of(TopicOne topicOne,
                                 TopicTwo topicTwo,
                                 TopicThree topicThree,
                                 LogOperatorOneTwo operatorOneTwo,
                                 LogOperatorOneThree operatorOneThree,
                                 LogOperatorTwoThree operatorTwoThree) {
        return new TopicTripleOneTwoThree(topicOne, topicTwo, topicThree, operatorOneTwo, operatorOneThree, operatorTwoThree);
    }

    /**
     * Factory Method for TopicQuadruple
     */

    public static TopicParams of(TopicZero topicZero,
                                 TopicOne topicOne,
                                 TopicTwo topicTwo,
                                 TopicThree topicThree,
                                 LogOperatorZeroOne operatorZeroOne,
                                 LogOperatorZeroTwo operatorZeroTwo,
                                 LogOperatorZeroThree operatorZeroThree,
                                 LogOperatorOneTwo operatorOneTwo,
                                 LogOperatorOneThree operatorOneThree,
                                 LogOperatorTwoThree operatorTwoThree) {
        return new TopicQuadruple(topicZero,
                topicOne,
                topicTwo,
                topicThree,
                operatorZeroOne,
                operatorZeroTwo,
                operatorZeroThree,
                operatorOneTwo,
                operatorOneThree,
                operatorTwoThree);
    }
}
