package io.api.etherscan.model.query.impl.topic.operator;

import io.api.etherscan.model.query.LogOp;

public class LogOperatorOneTwo extends BaseLogOperator {

    public LogOperatorOneTwo(LogOp operator) {
        super(LogOperatorParams.TOPIC_1_2, operator);
    }
}
