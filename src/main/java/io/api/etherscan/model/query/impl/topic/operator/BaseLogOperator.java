package io.api.etherscan.model.query.impl.topic.operator;

import io.api.etherscan.model.query.LogOp;
import io.api.etherscan.model.query.impl.topic.TopicParams;

public abstract class BaseLogOperator implements TopicParams {

    private final LogOperatorParams operatorParams;
    private final LogOp operator;

    public BaseLogOperator(LogOperatorParams operatorParams, LogOp operator) {
        this.operatorParams = operatorParams;
        this.operator = operator;
    }

    @Override
    public String getApiParams() {
        return operatorParams.getApiParameter() + operator.getOperation();
    }
}
