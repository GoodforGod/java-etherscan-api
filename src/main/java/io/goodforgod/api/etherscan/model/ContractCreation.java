package io.goodforgod.api.etherscan.model;

import java.util.Objects;

public class ContractCreation {

    private String contractAddress;
    private String contractCreator;
    private String txHash;

    protected ContractCreation() {}

    public String getContractAddress() {
        return contractAddress;
    }

    public String getContractCreator() {
        return contractCreator;
    }

    public String getTxHash() {
        return txHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ContractCreation that = (ContractCreation) o;
        return Objects.equals(contractAddress, that.contractAddress)
                && Objects.equals(contractCreator, that.contractCreator)
                && Objects.equals(txHash, that.txHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractAddress, contractCreator, txHash);
    }

    @Override
    public String toString() {
        return "ContractCreation{" +
                "contractAddress=" + contractAddress +
                ", contractCreator=" + contractCreator +
                ", txHash=" + txHash +
                '}';
    }

    public static ContractCreationBuilder builder() {
        return new ContractCreationBuilder();
    }

    public static final class ContractCreationBuilder {

        private String contractAddress;
        private String contractCreator;
        private String txHash;

        private ContractCreationBuilder() {}

        public ContractCreationBuilder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public ContractCreationBuilder withContractCreator(String contractCreator) {
            this.contractCreator = contractCreator;
            return this;
        }

        public ContractCreationBuilder withTxHash(String txHash) {
            this.txHash = txHash;
            return this;
        }

        public ContractCreation build() {
            ContractCreation contractCreation = new ContractCreation();
            contractCreation.contractAddress = contractAddress;
            contractCreation.contractCreator = contractCreator;
            contractCreation.txHash = txHash;
            return contractCreation;
        }
    }
}
