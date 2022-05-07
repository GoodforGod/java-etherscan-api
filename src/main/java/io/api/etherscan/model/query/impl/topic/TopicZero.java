package io.api.etherscan.model.query.impl.topic;

import io.api.etherscan.model.query.LogTopic;

public class TopicZero extends BaseTopic {

    public TopicZero(LogTopic logTopic) {
        super(TopicNr.TOPIC_0, logTopic);
    }
}
