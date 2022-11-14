package io.api.etherscan.core.impl;

import io.api.etherscan.core.IGasTrackerApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.model.GasOracle;
import io.api.etherscan.model.utility.GasOracleResponseTO;
import org.jetbrains.annotations.NotNull;

/**
 * GasTracker API Implementation
 *
 * @see IGasTrackerApi
 *
 * @author Abhay Gupta
 * @since 14.11.2022
 */
public class GasTrackerApiProvider extends BasicProvider implements IGasTrackerApi {

    private static final String ACT_GAS_ORACLE_PARAM = ACT_PREFIX + "gasoracle";

    GasTrackerApiProvider(final IQueueManager queue,
                          final String baseUrl,
                          final IHttpExecutor executor) {
        super(queue, "gastracker", baseUrl, executor);
    }

    @NotNull
    @Override
    public GasOracle gasoracle() throws ApiException {
        final GasOracleResponseTO response = getRequest(ACT_GAS_ORACLE_PARAM, GasOracleResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanException(response);

        return response.getResult();
    }
}
