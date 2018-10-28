package io.api.core.account;

import io.api.model.Balance;
import io.api.model.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#accounts
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public interface IAccountProvider {

    Optional<Balance> balance(String address);

    /**
     * Maximum 20 address for batch request
     * If address > 20, then there will be more than 1 request
     */
    List<Balance> balances(List<String> addresses);

    /** All transactions */
    List<Transaction> transactions(String address);
    /** Only last 10000 transactions */
    List<Transaction> transactions(String address, int startBlock);
    /** Only last 10000 transactions */
    List<Transaction> transactions(String address, int startBlock, int endBlock);

    /** All internal transactions */
    List<Transaction> transactionsInternal(String address);
    /** Only last 10000 transactions */
    List<Transaction> transactionsInternal(String address, int startBlock);
    /** Only last 10000 transactions */
    List<Transaction> transactionsInternal(String address, int startBlock, int endBlock);
}
