package io.api.etherscan.model.query.impl.topic;

enum TopicNr {
    TOPIC_0("&topic0="),
    TOPIC_1("&topic1="),
    TOPIC_2("&topic2="),
    TOPIC_3("&topic3=");

    private final String topicParam;

    TopicNr(String topicParam) {
        this.topicParam = topicParam;
    }

    public String getTopicParam() {
        return topicParam;
    }
}
