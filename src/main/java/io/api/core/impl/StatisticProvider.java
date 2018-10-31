package io.api.core.impl;

import io.api.core.IStatisticProvider;
import io.api.error.EtherScanException;
import io.api.executor.IHttpExecutor;
import io.api.manager.IQueueManager;
import io.api.model.Price;
import io.api.model.Supply;
import io.api.model.utility.PriceResponseTO;
import io.api.model.utility.StringResponseTO;
import io.api.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class StatisticProvider extends BasicProvider implements IStatisticProvider {

    private static final String ACT_SUPPLY_PARAM = ACT_PREFIX + "ethsupply";
    private static final String ACT_TOKEN_SUPPLY_PARAM = ACT_PREFIX + "tokensupply";
    private static final String ACT_LASTPRICE_PARAM = ACT_PREFIX + "ethprice";

    private static final String CONTRACT_ADDRESS_PARAM = "&contractaddress=";

    StatisticProvider(final IQueueManager queue,
                             final String baseUrl,
                             final IHttpExecutor executor) {
        super(queue, "stats", baseUrl, executor);
    }

    @NotNull
    @Override
    public Supply supply() {
        final StringResponseTO response = getRequest(ACT_SUPPLY_PARAM, StringResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanException(response.getMessage() + ", with status " + response.getStatus());

        return new Supply(new BigInteger(response.getResult()));
    }

    @NotNull
    @Override
    public BigInteger supply(final String contract) {
        BasicUtils.validateAddress(contract);

        final String urlParams = ACT_TOKEN_SUPPLY_PARAM + CONTRACT_ADDRESS_PARAM + contract;
        final StringResponseTO response = getRequest(urlParams, StringResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanException(response.getMessage() + ", with status " + response.getStatus());

        return new BigInteger(response.getResult());
    }

    @NotNull
    @Override
    public Price lastPrice() {
        final PriceResponseTO response = getRequest(ACT_LASTPRICE_PARAM, PriceResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanException(response.getMessage() + ", with status " + response.getStatus());

        return response.getResult();
    }
}
