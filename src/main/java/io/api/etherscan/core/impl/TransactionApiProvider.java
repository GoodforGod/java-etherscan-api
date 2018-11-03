package io.api.etherscan.core.impl;

import io.api.etherscan.core.ITransactionApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.model.Status;
import io.api.etherscan.model.utility.ReceiptStatusResponseTO;
import io.api.etherscan.model.utility.StatusResponseTO;
import io.api.etherscan.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Transaction API Implementation
 *
 * @author GoodforGod
 * @see ITransactionApi
 * @since 28.10.2018
 */
public class TransactionApiProvider extends BasicProvider implements ITransactionApi {

    private static final String ACT_EXEC_STATUS_PARAM = ACT_PREFIX + "getstatus";
    private static final String ACT_RECEIPT_STATUS_PARAM = ACT_PREFIX + "gettxreceiptstatus";

    private static final String TXHASH_PARAM = "&txhash=";

    TransactionApiProvider(final IQueueManager queue,
                           final String baseUrl,
                           final IHttpExecutor executor) {
        super(queue, "transaction", baseUrl, executor);
    }

    @NotNull
    @Override
    public Optional<Status> execStatus(final String txhash) throws ApiException {
        BasicUtils.validateTxHash(txhash);

        final String urlParams = ACT_EXEC_STATUS_PARAM + TXHASH_PARAM + txhash;
        final StatusResponseTO response = getRequest(urlParams, StatusResponseTO.class);
        BasicUtils.validateTxResponse(response);

        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<Boolean> receiptStatus(final String txhash) throws ApiException {
        BasicUtils.validateTxHash(txhash);

        final String urlParams = ACT_RECEIPT_STATUS_PARAM + TXHASH_PARAM + txhash;
        final ReceiptStatusResponseTO response = getRequest(urlParams, ReceiptStatusResponseTO.class);
        BasicUtils.validateTxResponse(response);

        return (response.getResult() == null || BasicUtils.isEmpty(response.getResult().getStatus()))
                ? Optional.empty()
                : Optional.of(response.getResult().getStatus().contains("1"));
    }
}
