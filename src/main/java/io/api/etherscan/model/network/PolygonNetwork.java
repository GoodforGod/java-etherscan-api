package io.api.etherscan.model.network;

public enum PolygonNetwork implements Network {
    MAINNET("api"),
    TESTNET("api-testnet");

    private final String domain;

    PolygonNetwork(String domain) {
        this.domain = domain;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public String getExplorerName() {
        return "polygonscan";
    }

    @Override
    public String getEnding() {
        return "com";
    }
}
