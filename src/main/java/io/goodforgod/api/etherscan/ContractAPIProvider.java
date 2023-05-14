package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.error.EtherScanResponseException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.Abi;
import io.goodforgod.api.etherscan.model.response.StringResponseTO;
import io.goodforgod.api.etherscan.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Contract API Implementation
 *
 * @see ContractAPI
 * @author GoodforGod
 * @since 28.10.2018
 */
final class ContractAPIProvider extends BasicProvider implements ContractAPI {

    private static final String ACT_ABI_PARAM = ACT_PREFIX + "getabi";

    private static final String ADDRESS_PARAM = "&address=";

    ContractAPIProvider(RequestQueueManager requestQueueManager,
                        String baseUrl,
                        EthHttpClient executor,
                        Converter converter) {
        super(requestQueueManager, "contract", baseUrl, executor, converter);
    }

    @NotNull
    @Override
    public Abi contractAbi(String address) throws EtherScanException {
        BasicUtils.validateAddress(address);

        final String urlParam = ACT_ABI_PARAM + ADDRESS_PARAM + address;
        final StringResponseTO response = getRequest(urlParam, StringResponseTO.class);
        if (response.getStatus() != 1 && response.getMessage().startsWith("NOTOK")) {
            throw new EtherScanResponseException(response);
        }

        return (response.getResult().startsWith("Contract sou"))
                ? Abi.nonVerified()
                : Abi.verified(response.getResult());
    }
}
