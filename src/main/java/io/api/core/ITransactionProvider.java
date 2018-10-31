package io.api.core;

import io.api.model.Status;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#transactions
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface ITransactionProvider {

    @NotNull Status execStatus(String txhash);

    boolean receiptStatus(String txhash);
}
