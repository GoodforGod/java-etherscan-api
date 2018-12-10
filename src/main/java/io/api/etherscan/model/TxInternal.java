package io.api.etherscan.model;

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

    public boolean haveError() {
        return isError == 1;
    }

    public String getErrCode() {
        return errCode;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "TxInternal{" +
                "type='" + type + '\'' +
                ", traceId=" + traceId +
                ", isError=" + isError +
                ", errCode='" + errCode + '\'' +
                '}';
    }
}
