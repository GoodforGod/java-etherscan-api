package io.api.core.impl;

import io.api.core.IStatisticProvider;
import io.api.executor.IHttpExecutor;
import io.api.manager.IQueueManager;
import io.api.model.Price;
import io.api.model.Supply;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class StatisticProvider extends BasicProvider implements IStatisticProvider {

    private static final String ACT_SUPPLY_PARAM = ACT_PARAM + "ethsupply";
    private static final String ACT_TOKEN_SUPPLY_PARAM = ACT_PARAM + "tokensupply";
    private static final String ACT_LASTPRICE_PARAM = ACT_PARAM + "ethprice";

    StatisticProvider(final IQueueManager queue,
                             final String baseUrl,
                             final IHttpExecutor executor) {
        super(queue, "stats", baseUrl, executor);
    }

    @NotNull
    @Override
    public Supply supply() {
        return null;
    }

    @NotNull
    @Override
    public Optional<BigInteger> supply(String contract) {
        return Optional.empty();
    }

    @NotNull
    @Override
    public Price lastPrice() {
        return null;
    }
}
