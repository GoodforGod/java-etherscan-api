package io.goodforgod.api.etherscan;

import java.net.URI;
import org.jetbrains.annotations.NotNull;

/**
 * @author GoodforGod
 * @since 28.10.2018
 */
public enum EthNetworks implements EthNetwork {

    MAINNET("api", "io"),
    GORLI("api-goerli", "io"),
    SEPOLIA("api-sepolia", "io");

    private final URI domain;

    EthNetworks(String domain, String extension) {
        this.domain = URI.create("https://" + domain + ".etherscan." + extension + "/api");
    }

    @Override
    public @NotNull URI domain() {
        return domain;
    }
}
