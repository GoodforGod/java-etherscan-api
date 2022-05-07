package io.api.etherscan.model.query.impl.topic.triple;

import io.api.etherscan.model.query.impl.topic.BaseTopic;
import io.api.etherscan.model.query.impl.topic.TopicParams;
import io.api.etherscan.model.query.impl.topic.operator.BaseLogOperator;

public abstract class BaseTopicTriple implements TopicParams {

    private final BaseTopic topicA;
    private final BaseTopic topicB;
    private final BaseTopic topicC;
    private final BaseLogOperator operatorAToB;
    private final BaseLogOperator operatorAToC;
    private final BaseLogOperator operatorBToC;

    public BaseTopicTriple(BaseTopic topicA,
                           BaseTopic topicB,
                           BaseTopic topicC,
                           BaseLogOperator operatorAToB,
                           BaseLogOperator operatorAToC,
                           BaseLogOperator operatorBToC) {
        this.topicA = topicA;
        this.topicB = topicB;
        this.topicC = topicC;
        this.operatorAToB = operatorAToB;
        this.operatorAToC = operatorAToC;
        this.operatorBToC = operatorBToC;
    }

    @Override
    public String getApiParams() {
        return topicA.getApiParams()
                + topicB.getApiParams()
                + topicC.getApiParams()
                + operatorAToB.getApiParams()
                + operatorAToC.getApiParams()
                + operatorBToC.getApiParams();
    }
}
