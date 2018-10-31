package io.api.core.impl;

import io.api.core.IContractProvider;
import io.api.error.EtherScanException;
import io.api.executor.IHttpExecutor;
import io.api.manager.IQueueManager;
import io.api.model.Abi;
import io.api.model.utility.StringResponseTO;
import io.api.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class ContractProvider extends BasicProvider implements IContractProvider {

    private static final String ACT_ABI_PARAM = ACT_PREFIX + "getabi";

    private static final String ADDRESS_PARAM = "&address=";

    ContractProvider(final IQueueManager queueManager,
                     final String baseUrl,
                     final IHttpExecutor executor) {
        super(queueManager, "contract", baseUrl, executor);
    }

    @NotNull
    @Override
    public Abi contractAbi(final String address) {
        BasicUtils.validateAddress(address);

        final String urlParam = ACT_ABI_PARAM + ADDRESS_PARAM + address;
        final StringResponseTO response = getRequest(urlParam, StringResponseTO.class);
        if (response.getStatus() != 1 && !"NOTOK".equals(response.getMessage()))
            throw new EtherScanException(response.getMessage() + ", with status " + response.getStatus());

        return BasicUtils.isEmpty(response.getResult())
                ? Abi.nonVerified()
                : Abi.verified(response.getResult());
    }
}
