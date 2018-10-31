package io.api.core;

import io.api.model.Price;
import io.api.model.Supply;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Optional;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#stats
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface IStatisticProvider {

    /** ERC20 Total Supply */
    @NotNull Optional<BigInteger> supply(String contract);

    /** Eth Total Supply */
    @NotNull Supply supply();

    /** Eth last USD and BTC price */
    @NotNull Price lastPrice();
}
