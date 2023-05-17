package io.goodforgod.api.etherscan.http;

import java.net.URI;
import org.jetbrains.annotations.NotNull;

/**
 * Http Client interface
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public interface EthHttpClient {

    /**
     * Performs a Http GET request
     *
     * @param uri as string
     * @return result as string
     */
    byte[] get(@NotNull URI uri);

    /**
     * Performs a Http POST request
     *
     * @param uri  as string
     * @param body to post
     * @return result as string
     */
    byte[] post(@NotNull URI uri, byte[] body);
}
