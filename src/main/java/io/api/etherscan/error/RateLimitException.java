package io.api.etherscan.error;

/**
 * ! NO DESCRIPTION !
 *
 * @author iSnow
 * @since 2020-10-06
 */
public class RateLimitException extends ApiException {

    public RateLimitException(String message) {
        super(message);
    }

}
