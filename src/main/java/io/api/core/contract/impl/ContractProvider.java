package io.api.core.contract.impl;

import io.api.core.BasicProvider;
import io.api.core.contract.IContractProvider;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class ContractProvider extends BasicProvider implements IContractProvider {

    public ContractProvider(final String apiKey) {
        super("contract", apiKey);
    }
}
