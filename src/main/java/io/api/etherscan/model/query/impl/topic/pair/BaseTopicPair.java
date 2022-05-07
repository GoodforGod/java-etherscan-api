package io.api.etherscan.model.query.impl.topic.pair;

import io.api.etherscan.model.query.impl.topic.BaseTopic;
import io.api.etherscan.model.query.impl.topic.TopicParams;
import io.api.etherscan.model.query.impl.topic.operator.BaseLogOperator;

public abstract class BaseTopicPair implements TopicParams {

    private final BaseTopic topicA;
    private final BaseTopic topicB;
    private final BaseLogOperator operator;

    public BaseTopicPair(BaseTopic topicA, BaseTopic topicB, BaseLogOperator operator) {
        this.topicA = topicA;
        this.topicB = topicB;
        this.operator = operator;
    }

    @Override
    public String getApiParams() {
        return topicA.getApiParams()
                + topicB.getApiParams()
                + operator.getApiParams();
    }
}
