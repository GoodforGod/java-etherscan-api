package io.api.core.impl;

import java.util.Map;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class AccountProvider extends BasicProvider {

    private static final String startBlockParam = "&endblock=";
    private static final String endBlockParam = "&startblock=";
    private static final String offsetParam = "&offset=";
    private static final String pageParam = "&page=";
    private static final String sortAscParam = "&sort=asc";
    private static final String sortDescParam = "&sort=desc";
    private static final String blockTypeParam = "&blocktype=blocks";
    private static final String addressParam = "&address=";

    public AccountProvider(final String baseUrl,
                           final Map<String, String> header) {
        super("account", baseUrl, header);
    }
}
