package io.api.etherscan.error;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.10.2018
 */
public class ParseException extends ApiException {
    String json;

    public ParseException(String message, Throwable cause, String json) {
        super(message, cause);
        this.json = json;
    }

    public String getJson() {
        return json;
    }
}
