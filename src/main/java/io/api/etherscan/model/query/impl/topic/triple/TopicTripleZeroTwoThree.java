package io.api.etherscan.model.query.impl.topic.triple;

import io.api.etherscan.model.query.impl.topic.TopicThree;
import io.api.etherscan.model.query.impl.topic.TopicTwo;
import io.api.etherscan.model.query.impl.topic.TopicZero;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorTwoThree;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorZeroThree;
import io.api.etherscan.model.query.impl.topic.operator.LogOperatorZeroTwo;

public class TopicTripleZeroTwoThree extends BaseTopicTriple {

    public TopicTripleZeroTwoThree(TopicZero topicZero,
                                   TopicTwo topicTwo,
                                   TopicThree topicThree,
                                   LogOperatorZeroTwo operatorZeroTwo,
                                   LogOperatorZeroThree operatorZeroThree,
                                   LogOperatorTwoThree operatorTwoThree) {
        super(topicZero, topicTwo, topicThree, operatorZeroTwo, operatorZeroThree, operatorTwoThree);
    }
}
