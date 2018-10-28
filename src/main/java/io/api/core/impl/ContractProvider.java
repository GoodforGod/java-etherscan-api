package io.api.core.impl;

import io.api.core.IContractProvider;
import io.api.util.BasicUtils;

import java.util.Map;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class ContractProvider extends BasicProvider implements IContractProvider {

    private static final String abiParam = "&action=getabi";
    private static final String addressParam = "&address=";

    public ContractProvider(final String baseUrl,
                            final Map<String, String> headers) {
        super("contract", baseUrl, headers);
    }

    @Override
    public String contractAbi(String address) {
        if(!BasicUtils.isAddress(address))
            throw new RuntimeException("Not address");

        return getRequest(addressParam + address + abiParam);
    }
}
