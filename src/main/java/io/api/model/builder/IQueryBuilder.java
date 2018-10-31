package io.api.model.builder;

import io.api.error.LogQueryException;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public interface IQueryBuilder {
    LogQuery build() throws LogQueryException;
}
