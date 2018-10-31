package io.api.core.impl;

import io.api.core.IBlockProvider;
import io.api.executor.IHttpExecutor;
import io.api.manager.IQueueManager;
import io.api.model.UncleBlock;
import io.api.model.utility.UncleBlockResponseTO;
import io.api.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class BlockProvider extends BasicProvider implements IBlockProvider {

    private static final String ACT_BLOCK_PARAM = ACT_PREFIX + "getblockreward";

    private static final String BLOCKNO_PARAM = "&blockno=";

    BlockProvider(final IQueueManager queueManager,
                  final String baseUrl,
                  final IHttpExecutor executor) {
        super(queueManager, "block", baseUrl, executor);
    }

    @NotNull
    @Override
    public UncleBlock uncles(long blockNumber) {
        final String urlParam = ACT_BLOCK_PARAM + BLOCKNO_PARAM + blockNumber;
        final UncleBlockResponseTO response = getRequest(urlParam, UncleBlockResponseTO.class);
        BasicUtils.validateTxResponse(response);

        return (response.getResult() == null)
                ? UncleBlock.EMPTY
                : response.getResult();
    }
}
