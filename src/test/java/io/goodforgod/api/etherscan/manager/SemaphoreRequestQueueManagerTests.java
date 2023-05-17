package io.goodforgod.api.etherscan.manager;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.manager.impl.FakeRequestQueueManager;
import io.goodforgod.api.etherscan.manager.impl.SemaphoreRequestQueueManager;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class SemaphoreRequestQueueManagerTests extends ApiRunner {

    @Test
    void fakeManager() {
        RequestQueueManager fakeManager = new FakeRequestQueueManager();
        fakeManager.takeTurn();
        fakeManager.takeTurn();
        fakeManager.takeTurn();
        fakeManager.takeTurn();
        fakeManager.takeTurn();
        fakeManager.takeTurn();
        assertNotNull(fakeManager);
    }

    @Test
    @Timeout(3500)
    void queueManager() {
        RequestQueueManager requestQueueManager = new SemaphoreRequestQueueManager(1, Duration.ofSeconds(3));
        requestQueueManager.takeTurn();
        requestQueueManager.takeTurn();
        assertNotNull(requestQueueManager);
    }

    @Test
    @Timeout(4500)
    void queueManagerWithDelay() {
        RequestQueueManager requestQueueManager = new SemaphoreRequestQueueManager(1, Duration.ofSeconds(2));
        requestQueueManager.takeTurn();
        requestQueueManager.takeTurn();
        assertNotNull(requestQueueManager);
    }
}
