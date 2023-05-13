package io.goodforgod.api.etherscan;

import java.net.URI;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 11.05.2023
 */
public interface EthNetwork {

    /**
     * @return URI for network domain like <a href="https://api.etherscan.io/api">EtherScan API</a>
     */
    @NotNull
    URI domain();
}
