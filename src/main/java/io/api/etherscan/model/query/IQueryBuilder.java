package io.api.etherscan.model.query;

import io.api.etherscan.error.LogQueryException;
import io.api.etherscan.model.query.impl.LogQuery;

/**
 * Builder, part of The Event Log API
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public interface IQueryBuilder {

    LogQuery build() throws LogQueryException;
}
