package io.api.core;

import io.api.model.Balance;
import io.api.model.Block;
import io.api.model.Tx;

import java.util.List;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#accounts
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public interface IAccountProvider {

    /** Address ETH balance */
    Balance balance(String address);

    /**
     * Maximum 20 address for batch request
     * If address > 20, then there will be more than 1 request performed
     */
    List<Balance> balances(List<String> addresses);

    /** All txs */
    List<Tx> txs(String address);
    /** Only last 10000 txs */
    List<Tx> txs(String address, long startBlock);
    List<Tx> txs(String address, long startBlock, long endBlock);

    /** All internal txs */
    List<Tx> txsInternal(String address);
    /** Only last 10000 internal txs */
    List<Tx> txsInternal(String address, long startBlock);
    List<Tx> txsInternal(String address, long startBlock, long endBlock);
    /** Only last 10000 internal txs by txhash */
    List<Tx> txsInternalByHash(String txhash);

    /** All token txs */
    List<Tx> txsToken(String address);
    /** Only last 10000 token txs */
    List<Tx> txsToken(String address, long startBlock);
    List<Tx> txsToken(String address, long startBlock, long endBlock);

    /** All blocks mined by address */
    List<Block> minedBlocks(String address);
}
