package io.api.etherscan.core.impl;

import io.api.etherscan.core.IBlockApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.model.UncleBlock;
import io.api.etherscan.model.utility.UncleBlockResponseTO;
import io.api.etherscan.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Block API Implementation
 *
 * @see IBlockApi
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class BlockApiProvider extends BasicProvider implements IBlockApi {

    private static final String ACT_BLOCK_PARAM = ACT_PREFIX + "getblockreward";

    private static final String BLOCKNO_PARAM = "&blockno=";

    BlockApiProvider(final IQueueManager queueManager,
                     final String baseUrl,
                     final IHttpExecutor executor) {
        super(queueManager, "block", baseUrl, executor);
    }

    @NotNull
    @Override
    public Optional<UncleBlock> uncles(long blockNumber) throws ApiException {
        final String urlParam = ACT_BLOCK_PARAM + BLOCKNO_PARAM + blockNumber;
        final String response = getRequest(urlParam);
        if (BasicUtils.isEmpty(response) || response.contains("NOTOK"))
            return Optional.empty();

        final UncleBlockResponseTO responseTO = convert(response, UncleBlockResponseTO.class);
        BasicUtils.validateTxResponse(responseTO);
        return (responseTO.getResult() == null || responseTO.getResult().isEmpty())
                ? Optional.empty()
                : Optional.of(responseTO.getResult());
    }
}
