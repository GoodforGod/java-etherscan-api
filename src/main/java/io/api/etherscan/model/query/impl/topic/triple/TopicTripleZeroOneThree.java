package io.api.etherscan.model.query.impl.topic.triple;

import io.api.etherscan.model.query.impl.topic.TopicOne;
import io.api.etherscan.model.query.impl.topic.TopicThree;
import io.api.etherscan.model.query.impl.topic.TopicZero;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorOneThree;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorZeroOne;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorZeroThree;

public class TopicTripleZeroOneThree extends BaseTopicTriple {

    public TopicTripleZeroOneThree(TopicZero topicZero,
                                   TopicOne topicOne,
                                   TopicThree topicThree,
                                   LogOperatorZeroOne operatorZeroOne,
                                   LogOperatorZeroThree operatorZeroThree,
                                   LogOperatorOneThree operatorOneThree) {
        super(topicZero, topicOne, topicThree, operatorZeroOne, operatorZeroThree, operatorOneThree);
    }
}
