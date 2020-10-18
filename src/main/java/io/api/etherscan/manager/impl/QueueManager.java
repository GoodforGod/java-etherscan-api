package io.api.etherscan.manager.impl;

import io.api.etherscan.manager.IQueueManager;

import java.util.concurrent.*;

/**
 * Queue Semaphore implementation with size and reset time as params
 * 
 * @see IQueueManager
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class QueueManager implements IQueueManager {

    public static final QueueManager DEFAULT_KEY_QUEUE = new QueueManager(1, 7);
    public static final QueueManager PERSONAL_KEY_QUEUE = new QueueManager(2, 1);

    private final Semaphore semaphore;

    public QueueManager(int size, int resetInSec) {
        this(size, resetInSec, resetInSec);
    }

    public QueueManager(int size, int queueResetTimeInSec, int delayInSec) {
        this.semaphore = new Semaphore(size);
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(releaseLocks(size), delayInSec, queueResetTimeInSec, TimeUnit.SECONDS);
    }

    @Override
    public void takeTurn() {
        semaphore.acquireUninterruptibly();
    }

    private Runnable releaseLocks(int toRelease) {
        return () -> semaphore.release(toRelease);
    }
}
