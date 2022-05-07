package io.api.etherscan.model.query.impl.topic.triple;

import io.api.etherscan.model.query.impl.topic.TopicOne;
import io.api.etherscan.model.query.impl.topic.TopicThree;
import io.api.etherscan.model.query.impl.topic.TopicTwo;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorOneThree;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorOneTwo;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorTwoThree;

public class TopicTripleOneTwoThree extends BaseTopicTriple {

    public TopicTripleOneTwoThree(TopicOne topicOne,
                                  TopicTwo topicTwo,
                                  TopicThree topicThree,
                                  LogOperatorOneTwo operatorOneTwo,
                                  LogOperatorOneThree operatorOneThree,
                                  LogOperatorTwoThree operatorTwoThree) {
        super(topicOne, topicTwo, topicThree, operatorOneTwo, operatorOneThree, operatorTwoThree);
    }
}
