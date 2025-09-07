package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.error.EtherScanResponseException;
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
 * @author GoodforGod
 * @see BlockAPI
 * @since 28.10.2018
 */
public class BlockAPIProvider extends BasicProvider implements BlockAPI {

    private static final String ACT_BLOCK_PARAM = ACT_PREFIX + "getblockreward";

    private static final String BLOCKNO_PARAM = "&blockno=";

    public BlockAPIProvider(RequestQueueManager requestQueueManager,
                            String baseUrl,
                            EthHttpClient executor,
                            Converter converter,
                            int retryCount) {
        super(requestQueueManager, "block", baseUrl, executor, converter, retryCount);
    }

    @NotNull
    @Override
    public Optional<BlockUncle> uncles(long blockNumber) throws EtherScanException {
        final String urlParam = ACT_BLOCK_PARAM + BLOCKNO_PARAM + blockNumber;
        try {
            final UncleBlockResponseTO responseTO = getResponse(urlParam, UncleBlockResponseTO.class);
            if (responseTO.getMessage().startsWith("NOTOK")) {
                return Optional.empty();
            }

            BasicUtils.validateTxResponse(responseTO);
            return (responseTO.getResult() == null || responseTO.getResult().isEmpty())
                    ? Optional.empty()
                    : Optional.of(responseTO.getResult());
        } catch (EtherScanResponseException e) {
            if (e.getResponse().getMessage().startsWith("NOTOK")) {
                return Optional.empty();
            } else {
                throw e;
            }
        }
    }
}
