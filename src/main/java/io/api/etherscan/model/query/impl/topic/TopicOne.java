package io.api.etherscan.model.query.impl.topic;

import io.api.etherscan.model.query.LogTopic;

public class TopicOne extends BaseTopic{

    public TopicOne(LogTopic logTopic) {
        super(TopicNr.TOPIC_1, logTopic);
    }
}
