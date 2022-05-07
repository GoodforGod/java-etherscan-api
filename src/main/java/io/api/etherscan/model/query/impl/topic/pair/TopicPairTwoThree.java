package io.api.etherscan.model.query.impl.topic.pair;

import io.api.etherscan.model.query.impl.topic.TopicThree;
import io.api.etherscan.model.query.impl.topic.TopicTwo;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorTwoThree;

public class TopicPairTwoThree extends BaseTopicPair {

    public TopicPairTwoThree(TopicTwo topicTwo, TopicThree topicThree, LogOperatorTwoThree operator) {
        super(topicTwo, topicThree, operator);
    }

}
