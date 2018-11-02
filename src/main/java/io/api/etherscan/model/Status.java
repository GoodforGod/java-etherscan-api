package io.api.etherscan.model;

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
}
