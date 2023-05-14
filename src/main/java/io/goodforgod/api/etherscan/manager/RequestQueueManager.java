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
    RequestQueueManager ANONYMOUS = new SemaphoreRequestQueueManager(1, Duration.ofMillis(5015L));

    /**
     * Is available for all registered free API KEYs
     * <a href="https://docs.etherscan.io/getting-started/viewing-api-usage-statistics">Free API KEY</a>
     */
    RequestQueueManager FREE_PLAN = new SemaphoreRequestQueueManager(5, Duration.ofMillis(1015L));
    RequestQueueManager STANDARD_PLAN = new SemaphoreRequestQueueManager(10, Duration.ofMillis(1015L));
    RequestQueueManager ADVANCED_PLAN = new SemaphoreRequestQueueManager(20, Duration.ofMillis(1015L));
    RequestQueueManager PROFESSIONAL_PLAN = new SemaphoreRequestQueueManager(30, Duration.ofMillis(1015L));

    RequestQueueManager UNLIMITED = new FakeRequestQueueManager();

    /**
     * Waits in queue for chance to take turn
     */
    void takeTurn();
}
