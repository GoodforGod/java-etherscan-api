package io.api.etherscan.model.query.impl.topic.quadruple;

import io.api.etherscan.model.query.impl.topic.*;
import io.api.etherscan.model.query.impl.topic.operator.*;

public class TopicQuadruple implements TopicParams {

    private final TopicZero topicZero;
    private final TopicOne topicOne;
    private final TopicTwo topicTwo;
    private final TopicThree topicThree;
    private final LogOperatorZeroOne operatorZeroOne;
    private final LogOperatorZeroTwo operatorZeroTwo;
    private final LogOperatorZeroThree operatorZeroThree;
    private final LogOperatorOneTwo operatorOneTwo;
    private final LogOperatorOneThree operatorOneThree;
    private final LogOperatorTwoThree operatorTwoThree;

    public TopicQuadruple(TopicZero topicZero,
                          TopicOne topicOne,
                          TopicTwo topicTwo,
                          TopicThree topicThree,
                          LogOperatorZeroOne operatorZeroOne,
                          LogOperatorZeroTwo operatorZeroTwo,
                          LogOperatorZeroThree operatorZeroThree,
                          LogOperatorOneTwo operatorOneTwo,
                          LogOperatorOneThree operatorOneThree,
                          LogOperatorTwoThree operatorTwoThree) {
        this.topicZero = topicZero;
        this.topicOne = topicOne;
        this.topicTwo = topicTwo;
        this.topicThree = topicThree;
        this.operatorZeroOne = operatorZeroOne;
        this.operatorZeroTwo = operatorZeroTwo;
        this.operatorZeroThree = operatorZeroThree;
        this.operatorOneTwo = operatorOneTwo;
        this.operatorOneThree = operatorOneThree;
        this.operatorTwoThree = operatorTwoThree;
    }

    @Override
    public String getApiParams() {
        return topicZero.getApiParams()
                + topicOne.getApiParams()
                + topicTwo.getApiParams()
                + topicThree.getApiParams()
                + operatorZeroOne.getApiParams()
                + operatorZeroTwo.getApiParams()
                + operatorZeroThree.getApiParams()
                + operatorOneTwo.getApiParams()
                + operatorOneThree.getApiParams()
                + operatorTwoThree.getApiParams();
    }
}
