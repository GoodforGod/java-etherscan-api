package io.api.etherscan.model.query.impl.topic.operator;

import io.api.etherscan.model.query.LogOp;

public class LogOperatorZeroOne extends BaseLogOperator{

    public LogOperatorZeroOne(LogOp operator) {
        super(LogOperatorParams.TOPIC_0_1, operator);
    }
}
