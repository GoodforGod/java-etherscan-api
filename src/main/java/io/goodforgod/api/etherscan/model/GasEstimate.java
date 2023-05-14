package io.goodforgod.api.etherscan.model;

import java.time.Duration;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 14.05.2023
 */
public class GasEstimate {

    private final Duration duration;

    public GasEstimate(long durationInSeconds) {
        this.duration = Duration.ofSeconds(durationInSeconds);
    }

    public GasEstimate(Duration duration) {
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof GasEstimate))
            return false;
        GasEstimate that = (GasEstimate) o;
        return Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration);
    }

    @Override
    public String toString() {
        return duration.toString();
    }
}
