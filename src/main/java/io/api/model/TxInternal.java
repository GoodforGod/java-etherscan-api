package io.api.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.10.2018
 */
public class TxInternal extends BaseTx {

    private String type;
    private long traceId;
    private int isError;
    private String errCode;

    //<editor-fold desc="Getters">
    public String getType() {
        return type;
    }

    public long getTraceId() {
        return traceId;
    }

    public int getIsError() {
        return isError;
    }

    public String getErrCode() {
        return errCode;
    }
    //</editor-fold>
}
