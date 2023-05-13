package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.BlockUncle;
import io.goodforgod.api.etherscan.model.response.UncleBlockResponseTO;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * Block API Implementation
 *
 * @see BlockAPI
 * @author GoodforGod
 * @since 28.10.2018
 */
final class BlockAPIProvider extends BasicProvider implements BlockAPI {

    private static final String ACT_BLOCK_PARAM = ACT_PREFIX + "getblockreward";

    private static final String BLOCKNO_PARAM = "&blockno=";

    BlockAPIProvider(RequestQueueManager requestQueueManager,
                     String baseUrl,
                     EthHttpClient executor,
                     Converter converter) {
        super(requestQueueManager, "block", baseUrl, executor, converter);
    }

    @NotNull
    @Override
    public Optional<BlockUncle> uncles(long blockNumber) throws EtherScanException {
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
