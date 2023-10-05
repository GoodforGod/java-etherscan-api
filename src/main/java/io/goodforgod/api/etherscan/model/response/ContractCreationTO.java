package io.goodforgod.api.etherscan.model.response;

public class ContractCreationTO {

    private String contractAddress;
    private String contractCreator;
    private String txHash;

    public String getContractAddress() {
        return contractAddress;
    }

    public String getContractCreator() {
        return contractCreator;
    }

    public String getTxHash() {
        return txHash;
    }
}
