package io.api.model.temporary;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.10.2018
 */
public class StringResponseTO {
    private int status;
    private String message;
    private String result;

    public StringResponseTO(int status, String message, String result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    //<editor-fold desc="Getters">
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getResult() {
        return result;
    }
    //</editor-fold>
}
