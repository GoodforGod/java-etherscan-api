package io.goodforgod.api.etherscan.manager.impl;

import io.goodforgod.api.etherscan.manager.RequestQueueManager;

/**
 * Fake queue manager, always give turns, when you have no limits
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class FakeRequestQueueManager implements RequestQueueManager {

    @Override
    public void takeTurn() {
        // no limit or await provided for fake impl so rate limit exception will be
        // thrown if too many calls
    }

    @Override
    public void close() {
        // do nothing
    }
}
