package io.api.etherscan.error;

/**
 * @author GoodforGod
 * @since 29.10.2018
 */
public class ParseException extends ApiException {

    private final String json;

    public ParseException(String message, Throwable cause, String json) {
        super(message, cause);
        this.json = json;
    }

    public String getJson() {
        return json;
    }
}
