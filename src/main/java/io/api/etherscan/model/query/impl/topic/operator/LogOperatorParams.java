package io.api.etherscan.model.query.impl.topic.operator;

public enum LogOperatorParams {
    TOPIC_0_1("&topic0_1_opr="),
    TOPIC_0_2("&topic0_2_opr="),
    TOPIC_0_3("&topic0_3_opr="),
    TOPIC_1_2("&topic1_2_opr="),
    TOPIC_1_3("&topic1_3_opr="),
    TOPIC_2_3("&topic2_3_opr=");

    private final String apiParameter;

    LogOperatorParams(String apiParameter) {
        this.apiParameter = apiParameter;
    }

    public String getApiParameter() {
        return apiParameter;
    }
}
