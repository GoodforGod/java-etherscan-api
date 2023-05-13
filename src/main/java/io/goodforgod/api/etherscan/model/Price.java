package io.goodforgod.api.etherscan.model;

import com.google.gson.annotations.Expose;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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

    public double inUsd() {
        return ethusd;
    }

    public double inBtc() {
        return ethbtc;
    }

    public LocalDateTime usdTimestamp() {
        if (_ethusd_timestamp == null)
            _ethusd_timestamp = LocalDateTime.ofEpochSecond(Long.parseLong(ethusd_timestamp), 0, ZoneOffset.UTC);
        return _ethusd_timestamp;
    }

    public LocalDateTime btcTimestamp() {
        if (_ethbtc_timestamp == null)
            _ethbtc_timestamp = LocalDateTime.ofEpochSecond(Long.parseLong(ethbtc_timestamp), 0, ZoneOffset.UTC);
        return _ethbtc_timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Price price = (Price) o;

        if (Double.compare(price.ethusd, ethusd) != 0)
            return false;
        if (Double.compare(price.ethbtc, ethbtc) != 0)
            return false;
        if (ethusd_timestamp != null
                ? !ethusd_timestamp.equals(price.ethusd_timestamp)
                : price.ethusd_timestamp != null)
            return false;
        return (ethbtc_timestamp != null
                ? !ethbtc_timestamp.equals(price.ethbtc_timestamp)
                : price.ethbtc_timestamp != null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(ethusd);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ethbtc);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ethusd_timestamp != null
                ? ethusd_timestamp.hashCode()
                : 0);
        result = 31 * result + (ethbtc_timestamp != null
                ? ethbtc_timestamp.hashCode()
                : 0);
        return result;
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

        public PriceBuilder withEthusd(double ethusd) {
            this.ethusd = ethusd;
            return this;
        }

        public PriceBuilder withEthbtc(double ethbtc) {
            this.ethbtc = ethbtc;
            return this;
        }

        public PriceBuilder withEthusdTimestamp(LocalDateTime ethusdTimestamp) {
            this.ethusdTimestamp = ethusdTimestamp;
            return this;
        }

        public PriceBuilder withEthbtcTimestamp(LocalDateTime ethbtcTimestamp) {
            this.ethbtcTimestamp = ethbtcTimestamp;
            return this;
        }

        public Price build() {
            Price price = new Price();
            price.ethbtc = this.ethbtc;
            price.ethbtc_timestamp = String.valueOf(this.ethbtcTimestamp.toEpochSecond(ZoneOffset.UTC));
            price._ethbtc_timestamp = this.ethbtcTimestamp;
            price.ethusd = this.ethusd;
            price.ethusd_timestamp = String.valueOf(this.ethusdTimestamp.toEpochSecond(ZoneOffset.UTC));
            price._ethusd_timestamp = this.ethusdTimestamp;
            return price;
        }
    }
}
