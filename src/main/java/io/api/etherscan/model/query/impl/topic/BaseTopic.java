package io.api.etherscan.model.query.impl.topic;

import io.api.etherscan.model.query.LogTopic;

public abstract class BaseTopic {

    private final TopicNr topicNr;
    private final LogTopic logTopic;

    public BaseTopic(TopicNr topicNr, LogTopic logTopic) {
        this.topicNr = topicNr;
        this.logTopic = logTopic;
    }

    public String getApiParams() {
        return topicNr.getTopicParam() + logTopic.getHex();
    }

}
