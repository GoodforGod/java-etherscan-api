package io.api.core;

import io.api.model.UncleBlock;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#blocks
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface IBlockProvider {

    /** Return uncle blocks */
    UncleBlock uncles(long blockNumber);
}
