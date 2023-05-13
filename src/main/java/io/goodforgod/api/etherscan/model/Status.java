package io.goodforgod.api.etherscan.model;

import java.util.Objects;

/**
 * Contract Execution Status
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Status {

    /**
     * "0" = Pass , isError":"1" = Error during Contract Execution
     */
    private int isError;
    private String errDescription;

    public boolean haveError() {
        return isError == 1;
    }

    public String getErrDescription() {
        return errDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Status status = (Status) o;

        if (isError != status.isError)
            return false;
        return Objects.equals(errDescription, status.errDescription);
    }

    @Override
    public int hashCode() {
        int result = isError;
        result = 31 * result + (errDescription != null
                ? errDescription.hashCode()
                : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Status{" +
                "isError=" + isError +
                ", errDescription='" + errDescription + '\'' +
                '}';
    }

    public static StatusBuilder builder() {
        return new StatusBuilder();
    }

    public static final class StatusBuilder {

        private int isError;
        private String errDescription;

        private StatusBuilder() {}

        public StatusBuilder withIsError(int isError) {
            this.isError = isError;
            return this;
        }

        public StatusBuilder withErrDescription(String errDescription) {
            this.errDescription = errDescription;
            return this;
        }

        public Status build() {
            Status status = new Status();
            status.isError = this.isError;
            status.errDescription = this.errDescription;
            return status;
        }
    }
}
