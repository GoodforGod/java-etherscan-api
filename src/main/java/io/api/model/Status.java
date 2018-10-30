package io.api.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Status {

    private int isError;
    private String errDescription;

    public boolean haveError() {
        return isError == 1;
    }

    public String getErrDescription() {
        return errDescription;
    }
}
