package io.goodforgod.api.etherscan.error;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class EtherScanException extends RuntimeException {

    public EtherScanException(String message) {
        super(message);
    }

    public EtherScanException(String message, Throwable cause) {
        super(message, cause);
    }
}
