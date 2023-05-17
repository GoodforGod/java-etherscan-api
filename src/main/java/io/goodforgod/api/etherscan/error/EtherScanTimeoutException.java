package io.goodforgod.api.etherscan.error;

/**
 * @author GoodforGod
 * @since 12.11.2018
 */
public class EtherScanTimeoutException extends EtherScanConnectionException {

    public EtherScanTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
