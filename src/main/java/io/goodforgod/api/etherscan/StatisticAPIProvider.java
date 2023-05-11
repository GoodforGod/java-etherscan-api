package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.error.EtherScanResponseException;
import io.goodforgod.api.etherscan.executor.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.Price;
import io.goodforgod.api.etherscan.model.Supply;
import io.goodforgod.api.etherscan.model.response.PriceResponseTO;
import io.goodforgod.api.etherscan.model.response.StringResponseTO;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.math.BigInteger;
import org.jetbrains.annotations.NotNull;

/**
 * Statistic API Implementation
 *
 * @see StatisticAPI
 * @author GoodforGod
 * @since 28.10.2018
 */
final class StatisticAPIProvider extends BasicProvider implements StatisticAPI {

    private static final String ACT_SUPPLY_PARAM = ACT_PREFIX + "ethsupply";
    private static final String ACT_TOKEN_SUPPLY_PARAM = ACT_PREFIX + "tokensupply";
    private static final String ACT_LASTPRICE_PARAM = ACT_PREFIX + "ethprice";

    private static final String CONTRACT_ADDRESS_PARAM = "&contractaddress=";

    StatisticAPIProvider(final RequestQueueManager queue,
                         final String baseUrl,
                         final EthHttpClient executor) {
        super(queue, "stats", baseUrl, executor);
    }

    @NotNull
    @Override
    public Supply supply() throws EtherScanException {
        final StringResponseTO response = getRequest(ACT_SUPPLY_PARAM, StringResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanResponseException(response);

        return new Supply(new BigInteger(response.getResult()));
    }

    @NotNull
    @Override
    public BigInteger supply(String contract) throws EtherScanException {
        BasicUtils.validateAddress(contract);

        final String urlParams = ACT_TOKEN_SUPPLY_PARAM + CONTRACT_ADDRESS_PARAM + contract;
        final StringResponseTO response = getRequest(urlParams, StringResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanResponseException(response);

        return new BigInteger(response.getResult());
    }

    @NotNull
    @Override
    public Price lastPrice() throws EtherScanException {
        final PriceResponseTO response = getRequest(ACT_LASTPRICE_PARAM, PriceResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanResponseException(response);

        return response.getResult();
    }
}
