package io.api.core;

import io.api.model.Status;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#transactions
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface ITransactionProvider {

    Status execStatus(String txhash);

    boolean receiptStatus(String txhash);
}
