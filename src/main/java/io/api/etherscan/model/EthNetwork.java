package io.api.etherscan.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public enum EthNetwork {

    MAINNET("api"),
    ROPSTEN("api-ropsten"),
    KOVAN("api-kovan"),
    TOBALABA("api-tobalaba"),
    GORLI("api-goerli"),
    RINKEBY("api-rinkeby");

    private final String domain;

    EthNetwork(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }
}
