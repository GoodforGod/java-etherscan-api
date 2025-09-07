package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.Status;
import io.goodforgod.api.etherscan.model.response.ReceiptStatusResponseTO;
import io.goodforgod.api.etherscan.model.response.StatusResponseTO;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * Transaction API Implementation
 *
 * @author GoodforGod
 * @see TransactionAPI
 * @since 28.10.2018
 */
public class TransactionAPIProvider extends BasicProvider implements TransactionAPI {

    private static final String ACT_EXEC_STATUS_PARAM = ACT_PREFIX + "getstatus";
    private static final String ACT_RECEIPT_STATUS_PARAM = ACT_PREFIX + "gettxreceiptstatus";

    private static final String TXHASH_PARAM = "&txhash=";

    public TransactionAPIProvider(RequestQueueManager queue,
                                  String baseUrl,
                                  EthHttpClient executor,
                                  Converter converter,
                                  int retryCount) {
        super(queue, "transaction", baseUrl, executor, converter, retryCount);
    }

    @NotNull
    @Override
    public Optional<Status> statusExec(@NotNull String txhash) throws EtherScanException {
        BasicUtils.validateTxHash(txhash);

        final String urlParams = ACT_EXEC_STATUS_PARAM + TXHASH_PARAM + txhash;
        final StatusResponseTO response = getResponse(urlParams, StatusResponseTO.class);
        BasicUtils.validateTxResponse(response);

        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<Boolean> statusReceipt(@NotNull String txhash) throws EtherScanException {
        BasicUtils.validateTxHash(txhash);

        final String urlParams = ACT_RECEIPT_STATUS_PARAM + TXHASH_PARAM + txhash;
        final ReceiptStatusResponseTO response = getResponse(urlParams, ReceiptStatusResponseTO.class);
        BasicUtils.validateTxResponse(response);

        return (response.getResult() == null || BasicUtils.isEmpty(response.getResult().getStatus()))
                ? Optional.empty()
                : Optional.of(response.getResult().getStatus().contains("1"));
    }
}
