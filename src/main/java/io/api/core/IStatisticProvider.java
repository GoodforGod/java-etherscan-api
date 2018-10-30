package io.api.core;

import io.api.model.Price;
import io.api.model.Supply;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#stats
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface IStatisticProvider {

    /** Eth Total Supply */
    Supply supply();

    /** Eth last USD and BTC price */
    Price lastPrice();
}
