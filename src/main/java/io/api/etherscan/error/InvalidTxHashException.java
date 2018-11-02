package io.api.etherscan.error;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 02.11.2018
 */
public class InvalidTxHashException extends ApiException {

    public InvalidTxHashException(String message) {
        super(message);
    }

    public InvalidTxHashException(String message, Throwable cause) {
        super(message, cause);
    }
}
