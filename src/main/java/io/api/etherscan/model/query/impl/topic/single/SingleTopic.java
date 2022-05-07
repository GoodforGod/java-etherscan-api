package io.api.etherscan.model.query.impl.topic.single;

import io.api.etherscan.model.query.impl.topic.BaseTopic;
import io.api.etherscan.model.query.impl.topic.TopicParams;

public class SingleTopic implements TopicParams {

    private final BaseTopic topic;

    public SingleTopic(BaseTopic topic) {
        this.topic = topic;
    }

    @Override
    public String getApiParams() {
        return topic.getApiParams();
    }
}
