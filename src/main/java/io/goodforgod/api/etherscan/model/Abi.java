package io.goodforgod.api.etherscan.model;

import io.goodforgod.api.etherscan.util.BasicUtils;
import java.util.Objects;

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
        if (!(o instanceof Abi))
            return false;
        Abi abi = (Abi) o;
        return isVerified == abi.isVerified && Objects.equals(contractAbi, abi.contractAbi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractAbi, isVerified);
    }

    @Override
    public String toString() {
        return "Abi{" +
                "contractAbi=" + contractAbi +
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
