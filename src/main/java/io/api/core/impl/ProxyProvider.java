package io.api.core.impl;

import io.api.core.IProxyProvider;
import io.api.executor.IHttpExecutor;
import io.api.manager.IQueueManager;
import io.api.model.proxy.BlockProxy;
import io.api.model.proxy.TxProxy;
import io.api.model.proxy.utility.BlockProxyTO;
import io.api.model.proxy.utility.StringProxyTO;
import io.api.model.proxy.utility.TxProxyTO;
import io.api.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class ProxyProvider extends BasicProvider implements IProxyProvider {

    private static final String ACT_BLOCKNO_PARAM = ACT_PREFIX + "eth_blockNumber";
    private static final String ACT_BY_BLOCKNO_PARAM = ACT_PREFIX + "eth_getBlockByNumber";
    private static final String ACT_UNCLE_BY_BLOCKNOINDEX_PARAM = ACT_PREFIX + "eth_getUncleByBlockNumberAndIndex";
    private static final String ACT_BLOCKTX_COUNT_PARAM = ACT_PREFIX + "eth_getBlockTransactionCountByNumber";
    private static final String ACT_TX_BY_HASH_PARAM = ACT_PREFIX + "eth_getTransactionByHash";
    private static final String ACT_TX_BY_BLOCKNOINDEX_PARAM = ACT_PREFIX + "eth_getTransactionByBlockNumberAndIndex";
    private static final String ACT_TX_COUNT_PARAM = ACT_PREFIX + "eth_getTransactionCount";
    private static final String ACT_SEND_RAW_TX_PARAM = ACT_PREFIX + "eth_sendRawTransaction";
    private static final String ACT_TX_RECEIPT_PARAM = ACT_PREFIX + "eth_getTransactionReceipt";
    private static final String ACT_CALL_PARAM = ACT_PREFIX + "eth_call";
    private static final String ACT_CODE_PARAM = ACT_PREFIX + "eth_getCode";
    private static final String ACT_STORAGEAT_PARAM = ACT_PREFIX + "eth_getStorageAt";
    private static final String ACT_GASPRICE_PARAM = ACT_PREFIX + "eth_gasPrice";
    private static final String ACT_ESTIMATEGAS_PARAM = ACT_PREFIX + "eth_estimateGas";

    private static final String BOOLEAN_PARAM = "&boolean=true";
    private static final String TAG_LAST_PARAM = "&tag=lastest";
    private static final String GASPRICE_PARAM = "&gasPrice=";
    private static final String POSITION_PARAM = "&position=";
    private static final String ADDRESS_PARAM = "&address=";
    private static final String TXHASH_PARAM = "&txhash=";
    private static final String INDEX_PARAM = "&index=";
    private static final String VALUE_PARAM = "&value=";
    private static final String DATA_PARAM = "&data=";
    private static final String GAS_PARAM = "&gas=";
    private static final String TAG_PARAM = "&tag=";
    private static final String HEX_PARAM = "&hex=";
    private static final String TO_PARAM = "&to=";

    ProxyProvider(final IQueueManager queue,
                  final String baseUrl,
                  final IHttpExecutor executor) {
        super(queue, "proxy", baseUrl,executor);
    }

    @Override
    public long blockNoLast() {
        return 0;
    }

    @NotNull
    @Override
    public Optional<BlockProxy> block(final long blockNo) {
        final long compBlockNo = BasicUtils.compensateMinBlock(blockNo);

        final String urlParams = ACT_BY_BLOCKNO_PARAM + TAG_PARAM + compBlockNo;
        final BlockProxyTO response = getRequest(urlParams, BlockProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<BlockProxy> blockUncle(final long blockNo, final long index) {
        final long compBlockNo = BasicUtils.compensateMinBlock(blockNo);
        final long compIndex = BasicUtils.compensateMinBlock(index);

        final String urlParams = ACT_UNCLE_BY_BLOCKNOINDEX_PARAM + TAG_PARAM + compBlockNo + INDEX_PARAM + compIndex;
        final BlockProxyTO response = getRequest(urlParams, BlockProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<TxProxy> tx(final String txhash) {
        BasicUtils.validateTxHash(txhash);

        final String urlParams = ACT_TX_BY_HASH_PARAM + TXHASH_PARAM + txhash;
        final TxProxyTO response = getRequest(urlParams, TxProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<TxProxy> tx(final long blockNo, final long index) {
        final long compBlockNo = BasicUtils.compensateMinBlock(blockNo);
        final long compIndex = BasicUtils.compensateMinBlock(index);

        final String urlParams = ACT_TX_BY_BLOCKNOINDEX_PARAM + TAG_PARAM + compBlockNo + INDEX_PARAM + compIndex;
        final TxProxyTO response = getRequest(urlParams, TxProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @Override
    public int txCount(final long blockNo) {
        final long compensatedBlockNo = BasicUtils.compensateMinBlock(blockNo);
        final String urlParams = ACT_TX_COUNT_PARAM + TAG_PARAM + compensatedBlockNo;
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return Integer.valueOf(response.getResult());
    }

    @Override
    public int txSendCount(final String address) {
        BasicUtils.validateAddress(address);

        final String urlParams = ACT_TX_BY_HASH_PARAM + ADDRESS_PARAM + address + TAG_LAST_PARAM;
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return (int) BasicUtils.parseHex(response.getResult());
    }

    //TODO need postRequest executor implementation
    @Override
    public boolean txSendRaw(final String hexEncodedTx) {
        return false;
    }

    @NotNull
    @Override
    public String call(final String address, final String data) {
        BasicUtils.validateAddress(address);

        final String urlParams = ACT_CALL_PARAM + TO_PARAM + address + DATA_PARAM + data + TAG_LAST_PARAM;
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return (BasicUtils.isEmpty(response.getResult()))
                ? ""
                : response.getResult();
    }

    @NotNull
    @Override
    public String code(final String address) {
        BasicUtils.validateAddress(address);

        final String urlParams = ACT_CODE_PARAM + ADDRESS_PARAM + address + TAG_LAST_PARAM;
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return (BasicUtils.isEmpty(response.getResult()))
                ? ""
                : response.getResult();
    }

    @NotNull
    @Override
    public String storageAt(final String address, final long position) {
        BasicUtils.validateAddress(address);
        final long compPosition = BasicUtils.compensateMinBlock(position);

        final String urlParams = ACT_STORAGEAT_PARAM + ADDRESS_PARAM + address + POSITION_PARAM + compPosition + TAG_LAST_PARAM;
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return (BasicUtils.isEmpty(response.getResult()))
                ? ""
                : response.getResult();
    }

    @NotNull
    @Override
    public BigInteger gasPrice() {
        final StringProxyTO response = getRequest(ACT_GASPRICE_PARAM, StringProxyTO.class);
        return (BasicUtils.isEmpty(response.getResult()))
                ? BigInteger.valueOf(-1)
                : BigInteger.valueOf(BasicUtils.parseHex(response.getResult()));
    }

    @NotNull
    @Override
    public BigInteger gasEstimated() {
        final String urlParams = ACT_STORAGEAT_PARAM + VALUE_PARAM + "0xff22"
                + GAS_PARAM + "0xffffff" + GASPRICE_PARAM + "0x151da038cc";
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return (BasicUtils.isEmpty(response.getResult()))
                ? BigInteger.valueOf(-1)
                : BigInteger.valueOf(BasicUtils.parseHex(response.getResult()));
    }
}
