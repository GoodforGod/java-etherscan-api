package io.api.etherscan.model.query.impl.topic.pair;

import io.api.etherscan.model.query.impl.topic.TopicOne;
import io.api.etherscan.model.query.impl.topic.TopicTwo;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorOneTwo;

public class TopicPairOneTwo extends BaseTopicPair {

    public TopicPairOneTwo(TopicOne topicOne, TopicTwo topicTwo, LogOperatorOneTwo operator) {
        super(topicOne, topicTwo, operator);
    }

}
