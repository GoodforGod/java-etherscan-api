package io.goodforgod.api.etherscan.error;

/**
 * @author GoodforGod
 * @since 29.10.2018
 */
public class EtherScanParseException extends EtherScanException {

    private final String json;

    public EtherScanParseException(String message, Throwable cause, String json) {
        super(message, cause);
        this.json = json;
    }

    public String getJson() {
        return json;
    }
}
