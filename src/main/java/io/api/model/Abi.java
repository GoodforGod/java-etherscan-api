package io.api.model;

import io.api.util.BasicUtils;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class Abi {
    private String contractAbi;
    private boolean isVerified;

    private Abi(String contractAbi, boolean isVerified) {
        this.contractAbi = contractAbi;
        this.isVerified = isVerified;
    }

    public static Abi verified(String contractAbi) {
        return new Abi(contractAbi, true);
    }

    public static Abi nonVerified() {
        return new Abi("", false);
    }

    public boolean haveAbi() {
        return !BasicUtils.isEmpty(contractAbi);
    }

    public String getContractAbi() {
        return contractAbi;
    }

    public boolean isVerified() {
        return isVerified;
    }
}
