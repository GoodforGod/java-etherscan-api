package io.api.etherscan.manager;

/**
 * Queue manager to support API limits (EtherScan 5request\sec limit) Managers
 * grants turn if the limit is not exhausted And resets queue each set period
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface IQueueManager {

    /**
     * Waits in queue for chance to take turn
     */
    void takeTurn();
}
