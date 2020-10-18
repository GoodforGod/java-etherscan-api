package io.api.etherscan.error;

/**
 * @author GoodforGod
 * @since 29.10.2018
 */
public class ConnectionException extends ApiException {

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
