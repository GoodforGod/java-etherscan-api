package io.goodforgod.api.etherscan.model;

import com.google.gson.annotations.Expose;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Price {

    private double ethusd;
    private double ethbtc;
    private String ethusd_timestamp;
    private String ethbtc_timestamp;
    @Expose(deserialize = false, serialize = false)
    private LocalDateTime _ethusd_timestamp;
    @Expose(deserialize = false, serialize = false)
    private LocalDateTime _ethbtc_timestamp;

    protected Price() {}

    public double inUsd() {
        return ethusd;
    }

    public double inBtc() {
        return ethbtc;
    }

    public LocalDateTime usdTimestamp() {
        if (_ethusd_timestamp == null && ethusd_timestamp != null) {
            _ethusd_timestamp = LocalDateTime.ofEpochSecond(Long.parseLong(ethusd_timestamp), 0, ZoneOffset.UTC);
        }
        return _ethusd_timestamp;
    }

    public LocalDateTime btcTimestamp() {
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
        return Double.compare(price.ethusd, ethusd) == 0 && Double.compare(price.ethbtc, ethbtc) == 0
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
                ", ethusd_timestamp='" + ethusd_timestamp + '\'' +
                ", ethbtc_timestamp='" + ethbtc_timestamp + '\'' +
                '}';
    }

    public static PriceBuilder builder() {
        return new PriceBuilder();
    }

    public static final class PriceBuilder {

        private double ethusd;
        private double ethbtc;
        private LocalDateTime ethusdTimestamp;
        private LocalDateTime ethbtcTimestamp;

        private PriceBuilder() {}

        public PriceBuilder withEthUsd(double ethusd) {
            this.ethusd = ethusd;
            return this;
        }

        public PriceBuilder withEthBtc(double ethbtc) {
            this.ethbtc = ethbtc;
            return this;
        }

        public PriceBuilder withEthUsdTimestamp(LocalDateTime ethusdTimestamp) {
            this.ethusdTimestamp = ethusdTimestamp;
            return this;
        }

        public PriceBuilder withEthBtcTimestamp(LocalDateTime ethbtcTimestamp) {
            this.ethbtcTimestamp = ethbtcTimestamp;
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
