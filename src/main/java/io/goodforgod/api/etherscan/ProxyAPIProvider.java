package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.error.EtherScanInvalidDataHexException;
import io.goodforgod.api.etherscan.error.EtherScanResponseException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.proxy.BlockProxy;
import io.goodforgod.api.etherscan.model.proxy.ReceiptProxy;
import io.goodforgod.api.etherscan.model.proxy.TxProxy;
import io.goodforgod.api.etherscan.model.proxy.utility.BlockProxyTO;
import io.goodforgod.api.etherscan.model.proxy.utility.StringProxyTO;
import io.goodforgod.api.etherscan.model.proxy.utility.TxInfoProxyTO;
import io.goodforgod.api.etherscan.model.proxy.utility.TxProxyTO;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.math.BigInteger;
import java.util.Optional;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

/**
 * Proxy API Implementation
 *
 * @see ProxyAPI
 * @author GoodforGod
 * @since 28.10.2018
 */
final class ProxyAPIProvider extends BasicProvider implements ProxyAPI {

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

    ProxyAPIProvider(RequestQueueManager queue,
                     String baseUrl,
                     EthHttpClient executor,
                     Converter converter) {
        super(queue, "proxy", baseUrl, executor, converter);
    }

    @Override
    public long blockNoLast() throws EtherScanException {
        final StringProxyTO response = getRequest(ACT_BLOCKNO_PARAM, StringProxyTO.class);
        return (BasicUtils.isEmpty(response.getResult()))
                ? -1
                : BasicUtils.parseHex(response.getResult()).longValue();
    }

    @NotNull
    @Override
    public Optional<BlockProxy> block(long blockNo) throws EtherScanException {
        final long compBlockNo = BasicUtils.compensateMinBlock(blockNo);

        final String urlParams = ACT_BY_BLOCKNO_PARAM + TAG_PARAM + compBlockNo + BOOLEAN_PARAM;
        final BlockProxyTO response = getRequest(urlParams, BlockProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<BlockProxy> blockUncle(long blockNo, long index) throws EtherScanException {
        final long compBlockNo = BasicUtils.compensateMinBlock(blockNo);
        final long compIndex = BasicUtils.compensateMinBlock(index);

        final String urlParams = ACT_UNCLE_BY_BLOCKNOINDEX_PARAM + TAG_PARAM
                + "0x" + Long.toHexString(compBlockNo) + INDEX_PARAM + "0x" + Long.toHexString(compIndex);
        final BlockProxyTO response = getRequest(urlParams, BlockProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<TxProxy> tx(String txhash) throws EtherScanException {
        BasicUtils.validateTxHash(txhash);

        final String urlParams = ACT_TX_BY_HASH_PARAM + TXHASH_PARAM + txhash;
        final TxProxyTO response = getRequest(urlParams, TxProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<TxProxy> tx(long blockNo, long index) throws EtherScanException {
        final long compBlockNo = BasicUtils.compensateMinBlock(blockNo);
        final long compIndex = (index < 1)
                ? 1
                : index;

        final String urlParams = ACT_TX_BY_BLOCKNOINDEX_PARAM + TAG_PARAM + compBlockNo + INDEX_PARAM + "0x"
                + Long.toHexString(compIndex);
        final TxProxyTO response = getRequest(urlParams, TxProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @Override
    public int txCount(long blockNo) throws EtherScanException {
        final long compensatedBlockNo = BasicUtils.compensateMinBlock(blockNo);
        final String urlParams = ACT_BLOCKTX_COUNT_PARAM + TAG_PARAM + "0x" + Long.toHexString(compensatedBlockNo);
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return BasicUtils.parseHex(response.getResult()).intValue();
    }

    @Override
    public int txSendCount(String address) throws EtherScanException {
        BasicUtils.validateAddress(address);

        final String urlParams = ACT_TX_COUNT_PARAM + ADDRESS_PARAM + address + TAG_LAST_PARAM;
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return BasicUtils.parseHex(response.getResult()).intValue();
    }

    @Override
    @NotNull
    public Optional<String> txSendRaw(String hexEncodedTx) throws EtherScanException {
        if (BasicUtils.isNotHex(hexEncodedTx))
            throw new EtherScanInvalidDataHexException("Data is not encoded in hex format - " + hexEncodedTx);

        final String urlParams = ACT_SEND_RAW_TX_PARAM + HEX_PARAM + hexEncodedTx;
        final StringProxyTO response = postRequest(urlParams, "", StringProxyTO.class);
        if (response.getError() != null)
            throw new EtherScanResponseException("Error occurred with code " + response.getError().getCode()
                    + " with message " + response.getError().getMessage()
                    + ", error id " + response.getId() + ", jsonRPC " + response.getJsonrpc());

        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<ReceiptProxy> txReceipt(String txhash) throws EtherScanException {
        BasicUtils.validateTxHash(txhash);

        final String urlParams = ACT_TX_RECEIPT_PARAM + TXHASH_PARAM + txhash;
        final TxInfoProxyTO response = getRequest(urlParams, TxInfoProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<String> call(String address, String data) throws EtherScanException {
        BasicUtils.validateAddress(address);
        if (BasicUtils.isNotHex(data))
            throw new EtherScanInvalidDataHexException("Data is not hex encoded.");

        final String urlParams = ACT_CALL_PARAM + TO_PARAM + address + DATA_PARAM + data + TAG_LAST_PARAM;
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<String> code(String address) throws EtherScanException {
        BasicUtils.validateAddress(address);

        final String urlParams = ACT_CODE_PARAM + ADDRESS_PARAM + address + TAG_LAST_PARAM;
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return Optional.ofNullable(response.getResult());
    }

    @NotNull
    @Override
    public Optional<String> storageAt(String address, long position) throws EtherScanException {
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
    public BigInteger gasPrice() throws EtherScanException {
        final StringProxyTO response = getRequest(ACT_GASPRICE_PARAM, StringProxyTO.class);
        return (BasicUtils.isEmpty(response.getResult()))
                ? BigInteger.valueOf(-1)
                : BasicUtils.parseHex(response.getResult());
    }

    @NotNull
    @Override
    public BigInteger gasEstimated() throws EtherScanException {
        return gasEstimated("606060405260728060106000396000f360606040526000");
    }

    @NotNull
    @Override
    public BigInteger gasEstimated(String hexData) throws EtherScanException {
        if (!BasicUtils.isEmpty(hexData) && BasicUtils.isNotHex(hexData))
            throw new EtherScanInvalidDataHexException("Data is not in hex format.");

        final String urlParams = ACT_ESTIMATEGAS_PARAM + DATA_PARAM + hexData + GAS_PARAM + "2000000000000000";
        final StringProxyTO response = getRequest(urlParams, StringProxyTO.class);
        return (BasicUtils.isEmpty(response.getResult()))
                ? BigInteger.valueOf(-1)
                : BasicUtils.parseHex(response.getResult());
    }
}
