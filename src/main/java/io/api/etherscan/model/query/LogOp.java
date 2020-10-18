package io.api.etherscan.model.query;

/**
 * Part of The Event Log API
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public enum LogOp {

    AND("and"),
    OR("or");

    private final String operation;

    LogOp(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
