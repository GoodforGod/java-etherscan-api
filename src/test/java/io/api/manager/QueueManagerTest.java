package io.api.manager;

import io.api.ApiRunner;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.manager.impl.FakeQueueManager;
import io.api.etherscan.manager.impl.QueueManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class QueueManagerTest extends ApiRunner {

    @Test
    void fakeManager() {
        IQueueManager fakeManager = new FakeQueueManager();
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
        IQueueManager queueManager = new QueueManager(1, 3);
        queueManager.takeTurn();
        queueManager.takeTurn();
        assertNotNull(queueManager);
    }

    @Test
    @Timeout(4500)
    void queueManagerWithDelay() {
        IQueueManager queueManager = new QueueManager(1, 2, 2);
        queueManager.takeTurn();
        queueManager.takeTurn();
        assertNotNull(queueManager);
    }

    @Test
    void queueManagerTimeout() {
        IQueueManager queueManager = new QueueManager(1, 3);
        queueManager.takeTurn();
        long start = System.currentTimeMillis();
        queueManager.takeTurn();
        long end = System.currentTimeMillis();
        assertEquals(3, Math.round((double) (end - start) / 1000));
    }
}
