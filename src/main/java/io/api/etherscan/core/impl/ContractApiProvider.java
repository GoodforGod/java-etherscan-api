package io.api.etherscan.core.impl;

import io.api.etherscan.core.IContractApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.model.Abi;
import io.api.etherscan.model.utility.StringResponseTO;
import io.api.etherscan.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Contract API Implementation
 *
 * @see IContractApi
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class ContractApiProvider extends BasicProvider implements IContractApi {

    private static final String ACT_ABI_PARAM = ACT_PREFIX + "getabi";

    private static final String ADDRESS_PARAM = "&address=";

    ContractApiProvider(final IQueueManager queueManager,
                        final String baseUrl,
                        final IHttpExecutor executor) {
        super(queueManager, "contract", baseUrl, executor);
    }

    @NotNull
    @Override
    public Abi contractAbi(final String address) throws ApiException {
        BasicUtils.validateAddress(address);

        final String urlParam = ACT_ABI_PARAM + ADDRESS_PARAM + address;
        final StringResponseTO response = getRequest(urlParam, StringResponseTO.class);
        if (response.getStatus() != 1 && "NOTOK".equals(response.getMessage()))
            throw new EtherScanException(response);

        return (response.getResult().startsWith("Contract sou"))
                ? Abi.nonVerified()
                : Abi.verified(response.getResult());
    }
}
