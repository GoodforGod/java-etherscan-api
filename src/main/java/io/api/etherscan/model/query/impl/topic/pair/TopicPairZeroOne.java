package io.api.etherscan.model.query.impl.topic.pair;

import io.api.etherscan.model.query.impl.topic.TopicOne;
import io.api.etherscan.model.query.impl.topic.TopicZero;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorZeroOne;

public class TopicPairZeroOne extends BaseTopicPair {

    public TopicPairZeroOne(TopicZero topicZero, TopicOne topicOne, LogOperatorZeroOne operator) {
        super(topicZero, topicOne, operator);
    }

}
