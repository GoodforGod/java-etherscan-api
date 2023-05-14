package io.goodforgod.api.etherscan.manager;

import io.goodforgod.api.etherscan.manager.impl.SemaphoreRequestQueueManager;
import java.time.Duration;

/**
 * Queue manager to support API limits (EtherScan 5request\sec limit) Managers grants turn if the
 * limit is not exhausted And resets queue each set period
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface RequestQueueManager extends AutoCloseable {

    RequestQueueManager DEFAULT = new SemaphoreRequestQueueManager(1, Duration.ofMillis(5005L));
    RequestQueueManager PERSONAL = new SemaphoreRequestQueueManager(5, Duration.ofMillis(1005L));

    /**
     * Waits in queue for chance to take turn
     */
    void takeTurn();
}
