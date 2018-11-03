package io.api.etherscan.manager.impl;

import io.api.etherscan.manager.IQueueManager;

/**
 * Fake queue manager, always give turns, when you have no limits
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class FakeQueueManager implements IQueueManager {

    @Override
    public boolean takeTurn() {
        return true;
    }
}
