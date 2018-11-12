package io.api.etherscan.error;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 12.11.2018
 */
public class ApiTimeoutException extends ApiException {

    public ApiTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
