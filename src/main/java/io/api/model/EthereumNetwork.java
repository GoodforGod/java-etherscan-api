package io.api.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public enum EthereumNetwork {
    MAINNET("api"),
    ROPSTEN("api-ropsten"),
    KOVAN("api-kovan"),
    RINKEBY("api-rinkeby");

    private final String domain;

    EthereumNetwork(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }
}
