package io.api.etherscan.manager.impl;

import io.api.etherscan.manager.IQueueManager;

import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Queue implementation
 * With size and reset time as params
 * @see IQueueManager
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class QueueManager implements IQueueManager {

    private static final Logger logger = Logger.getLogger(QueueManager.class.getName());

    private final int queueSize;
    private final BlockingQueue<Integer> queue;
    private final List<Integer> queueValues;

    private final ScheduledExecutorService queueExecutor;

    public QueueManager(int queueSize, int queueResetTimeInSec) {
        this(queueSize, queueResetTimeInSec, 0);
    }

    public QueueManager(int queueSize, int queueResetTimeInSec, int delayInSec) {
        this.queueSize = queueSize;
        this.queueValues = generateList(queueSize);
        this.queue = new ArrayBlockingQueue<>(queueSize);

        this.queueExecutor = Executors.newSingleThreadScheduledExecutor();
        this.queueExecutor.scheduleAtFixedRate(createTask(), delayInSec, queueResetTimeInSec, TimeUnit.SECONDS);
    }

    @Override
    public boolean takeTurn() {
        try {
            queue.take();
            return true;
        } catch (InterruptedException e) {
            logger.warning(e.getLocalizedMessage());
            return false;
        }
    }

    private Runnable createTask() {
            return () -> {
                try {
                    if(queue.size() == queueSize)
                        return;

                    queue.clear();
                    queue.addAll(queueValues);
                } catch (Exception e) {
                    logger.warning(e.getLocalizedMessage());
                }
            };
    }

    private List<Integer> generateList(int size) {
        return IntStream.range(0, size)
                .boxed()
                .collect(Collectors.toList());
    }
}
