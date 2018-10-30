package io.api.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.10.2018
 */
public class TxInternal {

    private String type;
    private String traceId;
    private String isError;
    private String errCode;

    //<editor-fold desc="Getters">
    public String getType() {
        return type;
    }

    public String getTraceId() {
        return traceId;
    }

    public String getIsError() {
        return isError;
    }

    public String getErrCode() {
        return errCode;
    }
    //</editor-fold>
}
