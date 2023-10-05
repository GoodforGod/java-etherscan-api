package io.goodforgod.api.etherscan.manager;

import io.goodforgod.api.etherscan.manager.impl.FakeRequestQueueManager;
import io.goodforgod.api.etherscan.manager.impl.SemaphoreRequestQueueManager;
import java.time.Duration;

/**
 * Queue manager to support API limits
 * Manager grants turn if the limit is not exhausted And resets queue each set period
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface RequestQueueManager extends AutoCloseable {

    /**
     * Is used by default when no API KEY is provided
     */
    static RequestQueueManager anonymous() {
        return new SemaphoreRequestQueueManager(1, Duration.ofMillis(5045L));
    }

    /**
     * Is available for all registered free API KEYs
     * <a href="https://docs.etherscan.io/getting-started/viewing-api-usage-statistics">Free API KEY</a>
     */
    static RequestQueueManager planFree() {
        return new SemaphoreRequestQueueManager(5, Duration.ofMillis(1045L));
    }

    static RequestQueueManager planStandard() {
        return new SemaphoreRequestQueueManager(10, Duration.ofMillis(1045L));
    }

    static RequestQueueManager planAdvanced() {
        return new SemaphoreRequestQueueManager(20, Duration.ofMillis(1045L));
    }

    static RequestQueueManager planProfessional() {
        return new SemaphoreRequestQueueManager(30, Duration.ofMillis(1045L));
    }

    static RequestQueueManager unlimited() {
        return new FakeRequestQueueManager();
    }

    /**
     * Waits in queue for chance to take turn
     */
    void takeTurn();
}
