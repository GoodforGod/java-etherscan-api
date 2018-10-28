package io.api.core;

import io.api.model.Balance;
import io.api.model.Block;
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

    /** Address ETH balance */
    Optional<Balance> balance(String address);

    /**
     * Maximum 20 address for batch request
     * If address > 20, then there will be more than 1 request
     */
    List<Balance> balances(List<String> addresses);

    /** All txs */
    List<Transaction> txs(String address);
    /** Only last 10000 txs */
    List<Transaction> txs(String address, int startBlock);
    /** Only last 10000 txs */
    List<Transaction> txs(String address, int startBlock, int endBlock);

    /** All internal txs */
    List<Transaction> txsInternal(String address);
    /** Only last 10000 internal txs */
    List<Transaction> txsInternal(String address, int startBlock);
    /** Only last 10000 internal txs */
    List<Transaction> txsInternal(String address, int startBlock, int endBlock);

    /** All token txs */
    List<Transaction> txsToken(String address);
    /** Only last 10000 token txs */
    List<Transaction> txsToken(String address, int startBlock);
    /** Only last 10000 token txs */
    List<Transaction> txsToken(String address, int startBlock, int endBlock);

    /** All blocks mined by address */
    List<Block> minedBlocks(String address);
}
