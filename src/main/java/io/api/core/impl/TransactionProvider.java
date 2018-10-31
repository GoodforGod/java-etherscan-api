package io.api.core.impl;

import io.api.core.ITransactionProvider;
import io.api.executor.IHttpExecutor;
import io.api.manager.IQueueManager;
import io.api.model.Status;
import io.api.model.utility.StatusResponseTO;
import io.api.model.utility.StringResponseTO;
import io.api.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class TransactionProvider extends BasicProvider implements ITransactionProvider {

    private static final String ACT_EXEC_STATUS_PARAM = ACT_PARAM + "getstatus";
    private static final String ACT_RECEIPT_STATUS_PARAM = ACT_PARAM + "gettxreceiptstatus";

    private static final String TXHASH_PARAM = "&txhash=";

    TransactionProvider(final IQueueManager queue,
                               final String baseUrl,
                               final IHttpExecutor executor) {
        super(queue, "transaction", baseUrl, executor);
    }

    @NotNull
    @Override
    public Status execStatus(final String txhash) {
        BasicUtils.validateTxHash(txhash);

        final String urlParams = ACT_EXEC_STATUS_PARAM + TXHASH_PARAM + txhash;
        final StatusResponseTO response = getRequest(urlParams, StatusResponseTO.class);
        BasicUtils.validateTxResponse(response);

        return (response.getResult() != null)
                ? response.getResult()
                : new Status();
    }

    @Override
    public boolean receiptStatus(final String txhash) {
        BasicUtils.validateTxHash(txhash);

        final String urlParams = ACT_RECEIPT_STATUS_PARAM + TXHASH_PARAM + txhash;
        final StringResponseTO response = getRequest(urlParams, StringResponseTO.class);
        BasicUtils.validateTxResponse(response);

        return (response.getResult().contains("1"));
    }
}
