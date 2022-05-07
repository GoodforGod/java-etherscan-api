package io.api.etherscan.model.query.impl;

import io.api.etherscan.error.LogQueryException;
import io.api.etherscan.model.query.IQueryBuilder;
import io.api.etherscan.model.query.impl.topic.TopicParams;

public class LogQueryWithTopicsBuilder implements IQueryBuilder {

    private final StandardLogQueryParams standardLogQueryParams;
    private final TopicParams topicParams;

    public LogQueryWithTopicsBuilder(StandardLogQueryParams standardLogQueryParams, TopicParams topicParams) {
        this.standardLogQueryParams = standardLogQueryParams;
        this.topicParams = topicParams;
    }

    @Override
    public LogQuery build() throws LogQueryException {
        return new LogQuery(standardLogQueryParams, topicParams);
    }
}
