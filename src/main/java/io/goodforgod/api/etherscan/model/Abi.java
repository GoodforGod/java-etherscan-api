package io.goodforgod.api.etherscan.model;

import io.goodforgod.api.etherscan.util.BasicUtils;

/**
 * @author GoodforGod
 * @since 31.10.2018
 */
public class Abi {

    private final String contractAbi;
    private final boolean isVerified;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Abi abi = (Abi) o;

        if (isVerified != abi.isVerified)
            return false;
        return contractAbi != null
                ? contractAbi.equals(abi.contractAbi)
                : abi.contractAbi == null;
    }

    @Override
    public int hashCode() {
        int result = contractAbi != null
                ? contractAbi.hashCode()
                : 0;
        result = 31 * result + (isVerified
                ? 1
                : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Abi{" +
                "contractAbi='" + contractAbi + '\'' +
                ", isVerified=" + isVerified +
                '}';
    }

    public static AbiBuilder builder() {
        return new AbiBuilder();
    }

    public static final class AbiBuilder {

        private String contractAbi;
        private boolean isVerified;

        private AbiBuilder() {}

        public AbiBuilder withContractAbi(String contractAbi) {
            this.contractAbi = contractAbi;
            return this;
        }

        public AbiBuilder withIsVerified(boolean isVerified) {
            this.isVerified = isVerified;
            return this;
        }

        public Abi build() {
            return new Abi(contractAbi, isVerified);
        }
    }
}