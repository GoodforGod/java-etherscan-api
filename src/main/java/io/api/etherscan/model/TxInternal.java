package io.api.etherscan.model;

import java.util.Objects;

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

    // <editor-fold desc="Getters">
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
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TxInternal))
            return false;
        if (!super.equals(o))
            return false;

        TxInternal that = (TxInternal) o;

        if (traceId != that.traceId)
            return false;
        return Objects.equals(errCode, that.errCode);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (traceId ^ (traceId >>> 32));
        result = 31 * result + (errCode != null ? errCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TxInternal{" +
                "type='" + type + '\'' +
                ", traceId=" + traceId +
                ", isError=" + isError +
                ", errCode='" + errCode + '\'' +
                "} " + super.toString();
    }
}
