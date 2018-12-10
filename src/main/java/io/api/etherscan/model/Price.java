package io.api.etherscan.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Price {

    private double ethusd;
    private double ethbtc;
    private String ethusd_timestamp;
    private String ethbtc_timestamp;
    private LocalDateTime _ethusd_timestamp;
    private LocalDateTime _ethbtc_timestamp;

    public double inUsd() {
        return ethusd;
    }

    public double inBtc() {
        return ethbtc;
    }

    public LocalDateTime usdTimestamp() {
        if(_ethusd_timestamp == null)
            _ethusd_timestamp = LocalDateTime.ofEpochSecond(Long.valueOf(ethusd_timestamp), 0, ZoneOffset.UTC);
        return _ethusd_timestamp;
    }

    public LocalDateTime btcTimestamp() {
        if(_ethbtc_timestamp == null)
            _ethbtc_timestamp = LocalDateTime.ofEpochSecond(Long.valueOf(ethbtc_timestamp), 0, ZoneOffset.UTC);
        return _ethbtc_timestamp;
    }

    @Override
    public String toString() {
        return "Price{" +
                "ethusd=" + ethusd +
                ", ethbtc=" + ethbtc +
                ", ethusd_timestamp='" + ethusd_timestamp + '\'' +
                ", ethbtc_timestamp='" + ethbtc_timestamp + '\'' +
                ", _ethusd_timestamp=" + _ethusd_timestamp +
                ", _ethbtc_timestamp=" + _ethbtc_timestamp +
                '}';
    }
}
