package io.api.etherscan.error;

/**
 * @author GoodforGod
 * @since 29.10.2018
 */
public class InvalidAddressException extends ApiException {

    public InvalidAddressException(String message) {
        super(message);
    }
}
