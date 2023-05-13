package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.error.EtherScanResponseException;
import io.goodforgod.api.etherscan.executor.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.GasOracle;
import io.goodforgod.api.etherscan.model.response.GasOracleResponseTO;
import org.jetbrains.annotations.NotNull;

/**
 * GasTracker API Implementation
 *
 * @see GasTrackerAPI
 * @author Abhay Gupta
 * @since 14.11.2022
 */
final class GasTrackerAPIProvider extends BasicProvider implements GasTrackerAPI {

    private static final String ACT_GAS_ORACLE_PARAM = ACT_PREFIX + "gasoracle";

    GasTrackerAPIProvider(RequestQueueManager queue,
                          String baseUrl,
                          EthHttpClient ethHttpClient) {
        super(queue, "gastracker", baseUrl, ethHttpClient);
    }

    @NotNull
    @Override
    public GasOracle oracle() throws EtherScanException {
        final GasOracleResponseTO response = getRequest(ACT_GAS_ORACLE_PARAM, GasOracleResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanResponseException(response);

        return response.getResult();
    }
}
