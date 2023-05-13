package io.goodforgod.api.etherscan.manager.impl;

import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Queue Semaphore implementation with size and reset time as params
 * 
 * @see RequestQueueManager
 * @author GoodforGod
 * @since 30.10.2018
 */
public final class SemaphoreRequestQueueManager implements RequestQueueManager, AutoCloseable {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final Semaphore semaphore;
    private final long queueResetTimeInMillis;

    public SemaphoreRequestQueueManager(int size, Duration resetIn) {
        this(size, resetIn, resetIn);
    }

    public SemaphoreRequestQueueManager(int size, Duration resetIn, Duration delayIn) {
        this(size, resetIn, delayIn, size);
    }

    public SemaphoreRequestQueueManager(int size, Duration queueResetTimeIn, Duration delayIn, int initialSize) {
        this.semaphore = new Semaphore(initialSize);
        this.queueResetTimeInMillis = queueResetTimeIn.toMillis();
        this.executorService.scheduleAtFixedRate(releaseLocks(size + 1),
                delayIn.toMillis(), queueResetTimeInMillis, TimeUnit.MILLISECONDS);
    }

    @SuppressWarnings("java:S899")
    @Override
    public void takeTurn() {
        try {
            semaphore.tryAcquire(queueResetTimeInMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private Runnable releaseLocks(int toRelease) {
        return () -> semaphore.release(toRelease);
    }

    @Override
    public void close() {
        executorService.shutdown();
    }
}
