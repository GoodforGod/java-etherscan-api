package io.api.core.account.impl;

import io.api.core.BasicProvider;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class AccountProvider extends BasicProvider {

    public AccountProvider(final String apiKey) {
        super("account", apiKey);
    }
}
