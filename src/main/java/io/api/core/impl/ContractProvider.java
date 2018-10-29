package io.api.core.impl;

import io.api.core.IContractProvider;
import io.api.model.temporary.StringResponseTO;
import io.api.util.BasicUtils;

import java.util.Map;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class ContractProvider extends BasicProvider implements IContractProvider {

    private static final String ABI_PARAM = "&action=getabi";
    private static final String ADDRESS_PARAM = "&address=";

    public ContractProvider(final String baseUrl,
                            final Map<String, String> headers) {
        super("contract", baseUrl, headers);
    }

    @Override
    public String contractAbi(String address) {
        BasicUtils.validateAddress(address);

        final String response = getRequest(ABI_PARAM + ADDRESS_PARAM + address);
        final StringResponseTO convert = convert(response, StringResponseTO.class);
        return convert.getMessage();
    }
}
