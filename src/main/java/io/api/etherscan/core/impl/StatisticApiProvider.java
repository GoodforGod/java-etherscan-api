package io.api.etherscan.core.impl;

import io.api.etherscan.core.IStatisticApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.model.Price;
import io.api.etherscan.model.Supply;
import io.api.etherscan.model.utility.PriceResponseTO;
import io.api.etherscan.model.utility.StringResponseTO;
import io.api.etherscan.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * Statistic API Implementation
 *
 * @see IStatisticApi
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class StatisticApiProvider extends BasicProvider implements IStatisticApi {

    private static final String ACT_SUPPLY_PARAM = ACT_PREFIX + "ethsupply";
    private static final String ACT_TOKEN_SUPPLY_PARAM = ACT_PREFIX + "tokensupply";
    private static final String ACT_LASTPRICE_PARAM = ACT_PREFIX + "ethprice";

    private static final String CONTRACT_ADDRESS_PARAM = "&contractaddress=";

    StatisticApiProvider(final IQueueManager queue,
                         final String baseUrl,
                         final IHttpExecutor executor) {
        super(queue, "stats", baseUrl, executor);
    }

    @NotNull
    @Override
    public Supply supply() throws ApiException {
        final StringResponseTO response = getRequest(ACT_SUPPLY_PARAM, StringResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanException(response);

        return new Supply(new BigInteger(response.getResult()));
    }

    @NotNull
    @Override
    public BigInteger supply(final String contract) throws ApiException {
        BasicUtils.validateAddress(contract);

        final String urlParams = ACT_TOKEN_SUPPLY_PARAM + CONTRACT_ADDRESS_PARAM + contract;
        final StringResponseTO response = getRequest(urlParams, StringResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanException(response);

        return new BigInteger(response.getResult());
    }

    @NotNull
    @Override
    public Price lastPrice() throws ApiException {
        final PriceResponseTO response = getRequest(ACT_LASTPRICE_PARAM, PriceResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanException(response);

        return response.getResult();
    }
}
