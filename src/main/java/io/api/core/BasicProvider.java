package io.api.core;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public abstract class BasicProvider {

    protected final String url;

    public BasicProvider(final String module,
                         final String apiKey) {
        this.url = "https://api.etherscan.io/api?module=" + module + "&apikey=" + apiKey + "&action=";
    }


}
