package io.goodforgod.api.etherscan.error;

/**
 * @author iSnow
 * @since 2020-10-06
 */
public class EtherScanRateLimitException extends EtherScanException {

    public EtherScanRateLimitException(String message) {
        super(message);
    }
}
