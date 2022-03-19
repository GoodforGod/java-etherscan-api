package io.api.etherscan.model;

import io.api.etherscan.model.network.Network;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public enum EthNetwork implements Network {

    MAINNET("api", "io"),
    ROPSTEN("api-ropsten", "io"),
    KOVAN("api-kovan", "io"),
    TOBALABA("api-tobalaba", "com"),
    GORLI("api-goerli", "io"),
    RINKEBY("api-rinkeby", "io");

    private final String domain;
    private final String ending;

    EthNetwork(String domain, String ending) {
        this.domain = domain;
        this.ending = ending;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public String getExplorerName() {
        return "etherscan";
    }

    @Override
    public String getEnding() {
        return ending;
    }
}
