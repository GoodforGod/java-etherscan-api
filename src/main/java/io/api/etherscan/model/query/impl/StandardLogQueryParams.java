package io.api.etherscan.model.query.impl;

public class StandardLogQueryParams {

    private final String address;
    private final long startBlock;
    private final long endBlock;

    public StandardLogQueryParams(String address, long startBlock, long endBlock) {
        this.address = address;
        this.startBlock = startBlock;
        this.endBlock = endBlock;
    }

    public String getApiParams() {
        return "&address=" + address
                + "&fromBlock=" + startBlock
                + "&toBlock=" + endBlock;
    }

}
