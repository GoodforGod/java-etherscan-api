package io.api.core.impl;

import io.api.executor.IHttpExecutor;
import io.api.manager.IQueueManager;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class ParityProvider extends BasicProvider {

    private static final String ACT_BLOCKNO_PARAM = ACT_PARAM + "eth_blockNumber";
    private static final String ACT_BY_BLOCKNO_PARAM = ACT_PARAM + "eth_getBlockByNumber";
    private static final String ACT_UNCLE_BY_BLOCKNOINDEX_PARAM = ACT_PARAM + "eth_getUncleByBlockNumberAndIndex";
    private static final String ACT_BLOCKTX_COUNT_PARAM = ACT_PARAM + "eth_getBlockTransactionCountByNumber";
    private static final String ACT_TX_BY_HASH_PARAM = ACT_PARAM + "eth_getTransactionByHash";
    private static final String ACT_TX_BY_BLOCKNOINDEX_PARAM = ACT_PARAM + "eth_getTransactionByBlockNumberAndIndex";
    private static final String ACT_TX_COUNT_PARAM = ACT_PARAM + "eth_getTransactionCount";
    private static final String ACT_SEND_RAW_TX_PARAM = ACT_PARAM + "eth_sendRawTransaction";
    private static final String ACT_TX_RECEIPT_PARAM = ACT_PARAM + "eth_getTransactionReceipt";
    private static final String ACT_CALL_PARAM = ACT_PARAM + "eth_call";
    private static final String ACT_CODE_PARAM = ACT_PARAM + "eth_getCode";
    private static final String ACT_STORAGEAT_PARAM = ACT_PARAM + "eth_getStorageAt";
    private static final String ACT_GASPRICE_PARAM = ACT_PARAM + "eth_gasPrice";
    private static final String ACT_ESTIMATEGAS_PARAM = ACT_PARAM + "eth_estimateGas";

    ParityProvider(final IQueueManager queue,
                          final String baseUrl,
                          final IHttpExecutor executor) {
        super(queue, "proxy", baseUrl,executor);
    }
}
