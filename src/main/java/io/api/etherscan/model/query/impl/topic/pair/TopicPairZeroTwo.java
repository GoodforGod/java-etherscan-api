package io.api.etherscan.model.query.impl.topic.pair;

import io.api.etherscan.model.query.impl.topic.TopicTwo;
import io.api.etherscan.model.query.impl.topic.TopicZero;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorZeroTwo;

public class TopicPairZeroTwo extends BaseTopicPair {

    public TopicPairZeroTwo(TopicZero topicZero, TopicTwo topicTwo, LogOperatorZeroTwo operator) {
        super(topicZero, topicTwo, operator);
    }

}
