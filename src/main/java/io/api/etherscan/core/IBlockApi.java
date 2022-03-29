package io.api.etherscan.core;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.model.UncleBlock;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions https://etherscan.io/apis#blocks
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface IBlockApi {

    /**
     * Return uncle blocks
     * 
     * @param blockNumber block number form 0 to last
     * @return optional uncle blocks
     * @throws ApiException parent exception class
     */
    @NotNull
    Optional<UncleBlock> uncles(long blockNumber) throws ApiException;
}
