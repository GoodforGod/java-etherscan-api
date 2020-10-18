package io.api.etherscan.error;

/**
 * @author GoodforGod
 * @since 02.11.2018
 */
public class InvalidTxHashException extends ApiException {

    public InvalidTxHashException(String message) {
        super(message);
    }
}
