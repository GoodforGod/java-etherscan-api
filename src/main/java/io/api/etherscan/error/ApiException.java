package io.api.etherscan.error;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
