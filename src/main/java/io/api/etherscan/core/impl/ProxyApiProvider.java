package io.api.etherscan.core.impl;

import io.api.etherscan.core.IProxyApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.error.InvalidDataHexException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.model.proxy.BlockProxy;
import io.api.etherscan.model.proxy.ReceiptProxy;
import io.api.etherscan.model.proxy.TxProxy;
import io.api.etherscan.model.proxy.utility.BlockProxyTO;
import io.api.etherscan.model.proxy.utility.StringProxyTO;
import io.api.etherscan.model.proxy.utility.TxInfoProxyTO;
import io.api.etherscan.model.proxy.utility.TxProxyTO;
import io.api.etherscan.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Proxy API Implementation
 *
 * @see IProxyApi
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class ProxyApiProvider extends BasicProvider implements IProxyApi {

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
    private static final String TAG_LAST_PARAM = "&tag=latest";
    private static final String POSITION_PARAM = "&position=";
    private static final String ADDRESS_PARAM = "&address=";
    private static final String TXHASH_PARAM = "&txhash=";
    private static final String INDEX_PARAM = "&index=";
    private static final String DATA_PARAM = "&data=";
    private static final String GAS_PARAM = "&gas=";
    private static final String TAG_PARAM = "&tag=";
    private static final String HEX_PARAM = "&hex=";
    private static final String TO_PARAM = "&to=";

    private static final Pattern EMPTY_HEX = Pattern.compile("0x0+");

    ProxyApiProvider(final IQueueManager queue,
                     final String baseUrl,
                     final IHttpExecutor executor) {
        super(queue, "proxy", baseUrl, executor);
    }

    @Override
    public long blockNoLast() throws ApiException {
        final StringProxyTO response = getRequest(ACT_BLOCKNO_PARAM, StringProxyTO.class);
        return (BasicUtils.isEmpty(response.getResult()))
                ? -1
                : BasicUtils.parseHex(response.getResult()).longValue();
    }

    @NotNull
    @Override
    public Optional<BlockProxy> block(final long blockNo) throws ApiException {
        final long compBlockNo = BasicUtils.compensateMinBlock(blockNo);

        final String urlParams = ACT_BY_BLOCKNO_PARAM + TAG_PARAM + compBlockNo + BOOLEAN_PARAM;
        final BlockProxyTO response = getRequest(urlParams, BlockProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<BlockProxy> blockUncle(final long blockNo, final long index) throws ApiException {
        final long compBlockNo = BasicUtils.compensateMinBlock(blockNo);
        final long compIndex = BasicUtils.compensateMinBlock(index);

        final String urlParams = ACT_UNCLE_BY_BLOCKNOINDEX_PARAM + TAG_PARAM
                + "0x" + Long.toHexString(compBlockNo) + INDEX_PARAM + "0x" + Long.toHexString(compIndex);
        final BlockProxyTO response = getRequest(urlParams, BlockProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<TxProxy> tx(final String txhash) throws ApiException {
        BasicUtils.validateTxHash(txhash);

        final String urlParams = ACT_TX_BY_HASH_PARAM + TXHASH_PARAM + txhash;
        final TxProxyTO response = getRequest(urlParams, TxProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<TxProxy> tx(final long blockNo, final long index) throws ApiException {
        final long compBlockNo = BasicUtils.compensateMinBlock(blockNo);
        final long compIndex = (index < 1) ? 1 : index;

        final String urlParams = ACT_TX_BY_BLOCKNOINDEX_PARAM + TAG_PARAM + compBlockNo + INDEX_PARAM + "0x"
                + Long.toHexString(compIndex);
        final TxProxyTO response = getRequest(urlParams, TxProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @Override
    public int txCount(final long blockNo) throws ApiException {
        final long compensatedBlockNo = BasicUtils.compensateMinBlock(blockNo);
        final String urlParams = ACT_BLOCKTX_COUNT_PARAM + TAG_PARAM + "0x" + Long.toHexString(compensatedBlockNo);
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return BasicUtils.parseHex(response.getResult()).intValue();
    }

    @Override
    public int txSendCount(final String address) throws ApiException {
        BasicUtils.validateAddress(address);

        final String urlParams = ACT_TX_COUNT_PARAM + ADDRESS_PARAM + address + TAG_LAST_PARAM;
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return BasicUtils.parseHex(response.getResult()).intValue();
    }

    @Override
    @NotNull
    public Optional<String> txSendRaw(final String hexEncodedTx) throws ApiException {
        if (BasicUtils.isNotHex(hexEncodedTx))
            throw new InvalidDataHexException("Data is not encoded in hex format - " + hexEncodedTx);

        final String urlParams = ACT_SEND_RAW_TX_PARAM + HEX_PARAM + hexEncodedTx;
        final StringProxyTO response = postRequest(urlParams, "", StringProxyTO.class);
        if (response.getError() != null)
            throw new EtherScanException("Error occurred with code " + response.getError().getCode()
                    + " with message " + response.getError().getMessage()
                    + ", error id " + response.getId() + ", jsonRPC " + response.getJsonrpc());

        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<ReceiptProxy> txReceipt(final String txhash) throws ApiException {
        BasicUtils.validateTxHash(txhash);

        final String urlParams = ACT_TX_RECEIPT_PARAM + TXHASH_PARAM + txhash;
        final TxInfoProxyTO response = getRequest(urlParams, TxInfoProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<String> call(final String address, final String data) throws ApiException {
        BasicUtils.validateAddress(address);
        if (BasicUtils.isNotHex(data))
            throw new InvalidDataHexException("Data is not hex encoded.");

        final String urlParams = ACT_CALL_PARAM + TO_PARAM + address + DATA_PARAM + data + TAG_LAST_PARAM;
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<String> code(final String address) throws ApiException {
        BasicUtils.validateAddress(address);

        final String urlParams = ACT_CODE_PARAM + ADDRESS_PARAM + address + TAG_LAST_PARAM;
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<String> storageAt(final String address, final long position) throws ApiException {
        BasicUtils.validateAddress(address);
        final long compPosition = BasicUtils.compensateMinBlock(position);

        final String urlParams = ACT_STORAGEAT_PARAM + ADDRESS_PARAM + address + POSITION_PARAM + compPosition + TAG_LAST_PARAM;
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return (BasicUtils.isEmpty(response.getResult()) || EMPTY_HEX.matcher(response.getResult()).matches())
                ? Optional.empty()
                : Optional.of(response.getResult());
    }

    @NotNull
    @Override
    public BigInteger gasPrice() throws ApiException {
        final StringProxyTO response = getRequest(ACT_GASPRICE_PARAM, StringProxyTO.class);
        return (BasicUtils.isEmpty(response.getResult()))
                ? BigInteger.valueOf(-1)
                : BasicUtils.parseHex(response.getResult());
    }

    @NotNull
    @Override
    public BigInteger gasEstimated() throws ApiException {
        return gasEstimated("606060405260728060106000396000f360606040526000");
    }

    @NotNull
    @Override
    public BigInteger gasEstimated(final String hexData) throws ApiException {
        if (!BasicUtils.isEmpty(hexData) && BasicUtils.isNotHex(hexData))
            throw new InvalidDataHexException("Data is not in hex format.");

        final String urlParams = ACT_ESTIMATEGAS_PARAM + DATA_PARAM + hexData + GAS_PARAM + "2000000000000000";
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return (BasicUtils.isEmpty(response.getResult()))
                ? BigInteger.valueOf(-1)
                : BasicUtils.parseHex(response.getResult());
    }
}
