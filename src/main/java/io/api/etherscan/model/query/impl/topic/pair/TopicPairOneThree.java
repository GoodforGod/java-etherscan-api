package io.api.etherscan.model.query.impl.topic.pair;

import io.api.etherscan.model.query.impl.topic.TopicOne;
import io.api.etherscan.model.query.impl.topic.TopicThree;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorOneThree;

public class TopicPairOneThree extends BaseTopicPair {

    public TopicPairOneThree(TopicOne topicOne, TopicThree topicThree, LogOperatorOneThree operator) {
        super(topicOne, topicThree, operator);
    }

}
