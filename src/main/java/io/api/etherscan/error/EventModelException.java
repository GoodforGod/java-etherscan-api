package io.api.etherscan.error;

public class EventModelException extends ApiException {

    public EventModelException(String message) {
        super(message);
    }

    public EventModelException(String message, Throwable cause) {
        super(message, cause);
    }
}
