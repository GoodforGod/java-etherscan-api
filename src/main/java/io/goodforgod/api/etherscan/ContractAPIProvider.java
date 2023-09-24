package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.error.EtherScanResponseException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.Abi;
import io.goodforgod.api.etherscan.model.ContractCreation;
import io.goodforgod.api.etherscan.model.response.ContractCreationResponseTO;
import io.goodforgod.api.etherscan.model.response.StringResponseTO;
import io.goodforgod.api.etherscan.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

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

    private static final String ACT_CONTRACT_CREATION_PARAM = "getcontractcreation";

    private static final String ACT_CONTRACT_CREATION = ACT_PREFIX + ACT_CONTRACT_CREATION_PARAM;

    private static final String ACT_CONTRACT_ADDRESSES_PARAM = "&contractaddresses=";

    ContractAPIProvider(RequestQueueManager requestQueueManager,
                        String baseUrl,
                        EthHttpClient executor,
                        Converter converter) {
        super(requestQueueManager, "contract", baseUrl, executor, converter);
    }

    @NotNull
    @Override
    public Abi contractAbi(@NotNull String address) throws EtherScanException {
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

    @NotNull
    @Override
    public List<ContractCreation> contractCreation(@NotNull List<String> contractAddresses) throws EtherScanException {
        BasicUtils.validateAddresses(contractAddresses);
        final String urlParam = ACT_CONTRACT_CREATION + ACT_CONTRACT_ADDRESSES_PARAM + BasicUtils.toAddressParam(contractAddresses);
        final ContractCreationResponseTO response = getRequest(urlParam, ContractCreationResponseTO.class);
        if (response.getStatus() != 1 && response.getMessage().startsWith("NOTOK")) {
            throw new EtherScanResponseException(response);
        }

        return response.getResult().stream()
                .map(to -> ContractCreation.builder()
                        .withContractCreator(to.getContractCreator())
                        .withContractAddress(to.getContractAddress())
                        .withTxHash(to.getTxHash())
                        .build()
                ).collect(Collectors.toList());
    }
}
