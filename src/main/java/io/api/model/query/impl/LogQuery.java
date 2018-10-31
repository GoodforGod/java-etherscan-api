package io.api.model.query.impl;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogQuery {

    private final String params;

    LogQuery(String params) {
        this.params = params;
    }

    public String getParams() {
        return params;
    }
}
