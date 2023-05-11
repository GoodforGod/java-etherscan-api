package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.model.BlockUncle;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions <a href="https://etherscan.io/apis#blocks">...</a>
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface BlockAPI {

    /**
     * Return uncle blocks
     * 
     * @param blockNumber block number form 0 to last
     * @return optional uncle blocks
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Optional<BlockUncle> uncles(long blockNumber) throws EtherScanException;
}
