package io.api.etherscan.model.query.impl.topic.triple;

import io.api.etherscan.model.query.impl.topic.TopicOne;
import io.api.etherscan.model.query.impl.topic.TopicTwo;
import io.api.etherscan.model.query.impl.topic.TopicZero;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorOneTwo;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorZeroOne;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorZeroTwo;

public class TopicTripleZeroOneTwo extends BaseTopicTriple {

    public TopicTripleZeroOneTwo(TopicZero topicZero,
                                 TopicOne topicOne,
                                 TopicTwo topicTwo,
                                 LogOperatorZeroOne operatorZeroOne,
                                 LogOperatorZeroTwo operatorZeroTwo,
                                 LogOperatorOneTwo operatorOneTwo) {
        super(topicZero, topicOne, topicTwo, operatorZeroOne, operatorZeroTwo, operatorOneTwo);
    }

}
