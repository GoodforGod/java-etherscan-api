package io.goodforgod.api.etherscan.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 29.10.2018
 */
public class TxInternal extends BaseTx {

    private BigInteger value;
    private String type;
    private String traceId;
    private int isError;
    private String errCode;

    // <editor-fold desc="Getters">
    public BigInteger getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public long getTraceId() {
        return (traceId == null)
                ? 0
                : Long.parseLong(traceId);
    }

    public String getTraceIdAsString() {
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
        return isError == that.isError && Objects.equals(value, that.value) && Objects.equals(type, that.type)
                && Objects.equals(traceId, that.traceId) && Objects.equals(errCode, that.errCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value, type, traceId, isError, errCode);
    }

    @Override
    public String toString() {
        return "TxInternal{" +
                "type='" + type + '\'' +
                ", traceId=" + traceId +
                ", value=" + value +
                ", isError=" + isError +
                ", errCode='" + errCode + '\'' +
                "} " + super.toString();
    }

    public static TxInternalBuilder builder() {
        return new TxInternalBuilder();
    }

    public static final class TxInternalBuilder {

        private long blockNumber;
        private LocalDateTime timeStamp;
        private String hash;
        private String from;
        private String to;
        private BigInteger value;
        private String contractAddress;
        private String input;
        private BigInteger gas;
        private BigInteger gasUsed;
        private String type;
        private String traceId;
        private int isError;
        private String errCode;

        private TxInternalBuilder() {}

        public TxInternalBuilder withBlockNumber(long blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public TxInternalBuilder with_timeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public TxInternalBuilder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public TxInternalBuilder withFrom(String from) {
            this.from = from;
            return this;
        }

        public TxInternalBuilder withTo(String to) {
            this.to = to;
            return this;
        }

        public TxInternalBuilder withValue(BigInteger value) {
            this.value = value;
            return this;
        }

        public TxInternalBuilder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public TxInternalBuilder withInput(String input) {
            this.input = input;
            return this;
        }

        public TxInternalBuilder withGas(BigInteger gas) {
            this.gas = gas;
            return this;
        }

        public TxInternalBuilder withGasUsed(BigInteger gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public TxInternalBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public TxInternalBuilder withTraceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public TxInternalBuilder withIsError(int isError) {
            this.isError = isError;
            return this;
        }

        public TxInternalBuilder withErrCode(String errCode) {
            this.errCode = errCode;
            return this;
        }

        public TxInternal build() {
            TxInternal txInternal = new TxInternal();
            txInternal.gas = this.gas;
            txInternal.hash = this.hash;
            txInternal.gasUsed = this.gasUsed;
            txInternal.traceId = this.traceId;
            txInternal.type = this.type;
            txInternal.from = this.from;
            txInternal.contractAddress = this.contractAddress;
            txInternal.value = this.value;
            txInternal.timeStamp = String.valueOf(this.timeStamp.toEpochSecond(ZoneOffset.UTC));
            txInternal.errCode = this.errCode;
            txInternal.blockNumber = this.blockNumber;
            txInternal._timeStamp = this.timeStamp;
            txInternal.isError = this.isError;
            txInternal.to = this.to;
            txInternal.input = this.input;
            return txInternal;
        }
    }
}
