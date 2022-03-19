package io.api.etherscan.model.network;

public interface Network {

    String getDomain();

    String getExplorerName();

    String getEnding();

    default String getUrl() {
        return "https://" + getDomain() + "." + getExplorerName() + "." + getEnding() + "/api?apikey=";
    }
}
