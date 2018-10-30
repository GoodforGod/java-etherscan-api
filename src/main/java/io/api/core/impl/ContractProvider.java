package io.api.core.impl;

import io.api.core.IContractProvider;
import io.api.error.EtherScanException;
import io.api.manager.IQueueManager;
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

    private static final String ACTION_ABI_PARAM = "&action=getabi";
    private static final String ADDRESS_PARAM = "&address=";

    public ContractProvider(final IQueueManager queueManager,
                            final String baseUrl,
                            final Map<String, String> headers) {
        super(queueManager, "contract", baseUrl, headers);
    }

    @Override
    public String contractAbi(final String address) {
        BasicUtils.validateAddress(address);

        final String urlParam = ACTION_ABI_PARAM + ADDRESS_PARAM + address;
        final StringResponseTO converted = getRequest(urlParam, StringResponseTO.class);
        if (converted.getStatus() != 1)
            throw new EtherScanException(converted.getMessage() + ", with status " + converted.getStatus());

        return converted.getResult();
    }
}
