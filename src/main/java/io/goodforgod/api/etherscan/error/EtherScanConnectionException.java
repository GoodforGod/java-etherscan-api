package io.goodforgod.api.etherscan.error;

/**
 * @author GoodforGod
 * @since 29.10.2018
 */
public class EtherScanConnectionException extends EtherScanException {

    public EtherScanConnectionException(String message) {
        super(message);
    }

    public EtherScanConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
