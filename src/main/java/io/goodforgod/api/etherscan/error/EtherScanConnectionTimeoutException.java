package io.goodforgod.api.etherscan.error;

/**
 * @author GoodforGod
 * @since 12.11.2018
 */
public class EtherScanConnectionTimeoutException extends EtherScanConnectionException {

    public EtherScanConnectionTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
