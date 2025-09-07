package io.goodforgod.api.etherscan.model;

import com.google.gson.annotations.Expose;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Price {

    private BigDecimal ethusd;
    private BigDecimal ethbtc;
    private String ethusd_timestamp;
    private String ethbtc_timestamp;
    @Expose(deserialize = false, serialize = false)
    private LocalDateTime _ethusd_timestamp;
    @Expose(deserialize = false, serialize = false)
    private LocalDateTime _ethbtc_timestamp;

    protected Price() {}

    public BigDecimal inUsd() {
        return ethusd;
    }

    public BigDecimal inBtc() {
        return ethbtc;
    }

    public LocalDateTime timestampUsd() {
        if (_ethusd_timestamp == null && ethusd_timestamp != null) {
            _ethusd_timestamp = LocalDateTime.ofEpochSecond(Long.parseLong(ethusd_timestamp), 0, ZoneOffset.UTC);
        }
        return _ethusd_timestamp;
    }

    public LocalDateTime timestampBtc() {
        if (_ethbtc_timestamp == null && ethbtc_timestamp != null) {
            _ethbtc_timestamp = LocalDateTime.ofEpochSecond(Long.parseLong(ethbtc_timestamp), 0, ZoneOffset.UTC);
        }
        return _ethbtc_timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Price))
            return false;
        Price price = (Price) o;
        return Objects.equals(ethusd, price.ethusd) && Objects.equals(ethbtc, price.ethbtc)
                && Objects.equals(ethusd_timestamp, price.ethusd_timestamp)
                && Objects.equals(ethbtc_timestamp, price.ethbtc_timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ethusd, ethbtc, ethusd_timestamp, ethbtc_timestamp);
    }

    @Override
    public String toString() {
        return "Price{" +
                "ethusd=" + ethusd +
                ", ethbtc=" + ethbtc +
                ", ethusd_timestamp=" + ethusd_timestamp +
                ", ethbtc_timestamp=" + ethbtc_timestamp +
                '}';
    }

    public static PriceBuilder builder() {
        return new PriceBuilder();
    }

    public static class PriceBuilder {

        private BigDecimal ethusd;
        private BigDecimal ethbtc;
        private LocalDateTime ethusdTimestamp;
        private LocalDateTime ethbtcTimestamp;

        private PriceBuilder() {}

        public PriceBuilder withUsd(BigDecimal ethToUsd) {
            this.ethusd = ethToUsd;
            return this;
        }

        public PriceBuilder withBtc(BigDecimal ethToBtc) {
            this.ethbtc = ethToBtc;
            return this;
        }

        public PriceBuilder withTimestampUsd(LocalDateTime ethToUsdTimestamp) {
            this.ethusdTimestamp = ethToUsdTimestamp;
            return this;
        }

        public PriceBuilder withTimestampBtc(LocalDateTime ethToBtcTimestamp) {
            this.ethbtcTimestamp = ethToBtcTimestamp;
            return this;
        }

        public Price build() {
            Price price = new Price();
            price.ethbtc = this.ethbtc;
            price.ethusd = this.ethusd;
            if (this.ethbtcTimestamp != null) {
                price.ethbtc_timestamp = String.valueOf(this.ethbtcTimestamp.toEpochSecond(ZoneOffset.UTC));
                price._ethbtc_timestamp = this.ethbtcTimestamp;
            }
            if (this.ethusdTimestamp != null) {
                price.ethusd_timestamp = String.valueOf(this.ethusdTimestamp.toEpochSecond(ZoneOffset.UTC));
                price._ethusd_timestamp = this.ethusdTimestamp;
            }
            return price;
        }
    }
}
