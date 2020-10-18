package io.api.manager;

import io.api.ApiRunner;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.manager.impl.FakeQueueManager;
import io.api.etherscan.manager.impl.QueueManager;
import org.junit.Test;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class QueueManagerTest extends ApiRunner {

    @Test
    public void fakeManager() {
        IQueueManager fakeManager = new FakeQueueManager();
        fakeManager.takeTurn();
        fakeManager.takeTurn();
        fakeManager.takeTurn();
        fakeManager.takeTurn();
        fakeManager.takeTurn();
        fakeManager.takeTurn();
    }

    @Test(timeout = 3500)
    public void queueManager() {
        IQueueManager queueManager = new QueueManager(1, 3);
        queueManager.takeTurn();
        queueManager.takeTurn();
    }

    @Test(timeout = 4500)
    public void queueManagerWithDelay() {
        IQueueManager queueManager = new QueueManager(1, 2, 2);
        queueManager.takeTurn();
        queueManager.takeTurn();
    }

    @Test
    public void queueManagerTimeout() {
        IQueueManager queueManager = new QueueManager(1, 3);
        queueManager.takeTurn();
        long start = System.currentTimeMillis();
        queueManager.takeTurn();
        long end = System.currentTimeMillis();
        assertEquals(3, Math.round((double) (end - start) / 1000));
    }
}
