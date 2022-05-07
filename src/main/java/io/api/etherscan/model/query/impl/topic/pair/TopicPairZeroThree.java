package io.api.etherscan.model.query.impl.topic.pair;

import io.api.etherscan.model.query.impl.topic.TopicThree;
import io.api.etherscan.model.query.impl.topic.TopicZero;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorZeroThree;

public class TopicPairZeroThree extends BaseTopicPair {

    public TopicPairZeroThree(TopicZero topicZero, TopicThree topicThree, LogOperatorZeroThree operator) {
        super(topicZero, topicThree, operator);
    }

}
