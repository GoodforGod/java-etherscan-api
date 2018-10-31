package io.api.model.query;

import io.api.error.LogQueryException;
import io.api.model.query.impl.LogQuery;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public interface IQueryBuilder {
    LogQuery build() throws LogQueryException;
}
