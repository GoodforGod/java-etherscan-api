package io.api.etherscan.core.impl;

import io.api.etherscan.core.IAccountApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.model.*;
import io.api.etherscan.model.utility.*;
import io.api.etherscan.util.BasicUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Account API Implementation
 *
 * @see IAccountApi
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class AccountApiProvider extends BasicProvider implements IAccountApi {

    private static final int OFFSET_MAX = 10000;

    private static final String ACT_BALANCE_ACTION = ACT_PREFIX + "balance";
    private static final String ACT_TOKEN_BALANCE_PARAM = ACT_PREFIX + "tokenbalance";
    private static final String ACT_BALANCE_MULTI_ACTION = ACT_PREFIX + "balancemulti";
    private static final String ACT_TX_ACTION = ACT_PREFIX + "txlist";
    private static final String ACT_TX_INTERNAL_ACTION = ACT_PREFIX + "txlistinternal";
    private static final String ACT_TX_TOKEN_ACTION = ACT_PREFIX + "tokentx";
    private static final String ACT_TX_NFT_TOKEN_ACTION = ACT_PREFIX + "tokennfttx";
    private static final String ACT_MINED_ACTION = ACT_PREFIX + "getminedblocks";

    private static final String BLOCK_TYPE_PARAM = "&blocktype=blocks";
    private static final String CONTRACT_PARAM = "&contractaddress=";
    private static final String START_BLOCK_PARAM = "&startblock=";
    private static final String TAG_LATEST_PARAM = "&tag=latest";
    private static final String END_BLOCK_PARAM = "&endblock=";
    private static final String SORT_DESC_PARAM = "&sort=desc";
    private static final String SORT_ASC_PARAM = "&sort=asc";
    private static final String ADDRESS_PARAM = "&address=";
    private static final String TXHASH_PARAM = "&txhash=";
    private static final String OFFSET_PARAM = "&offset=";
    private static final String PAGE_PARAM = "&page=";

    AccountApiProvider(final IQueueManager queueManager,
                       final String baseUrl,
                       final IHttpExecutor executor) {
        super(queueManager, "account", baseUrl, executor);
    }

    @NotNull
    @Override
    public Balance balance(final String address) throws ApiException {
        BasicUtils.validateAddress(address);

        final String urlParams = ACT_BALANCE_ACTION + TAG_LATEST_PARAM + ADDRESS_PARAM + address;
        final StringResponseTO response = getRequest(urlParams, StringResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanException(response);

        return new Balance(address, new BigInteger(response.getResult()));
    }

    @NotNull
    @Override
    public TokenBalance balance(final String address, final String contract) throws ApiException {
        BasicUtils.validateAddress(address);
        BasicUtils.validateAddress(contract);

        final String urlParams = ACT_TOKEN_BALANCE_PARAM + ADDRESS_PARAM + address + CONTRACT_PARAM + contract;
        final StringResponseTO response = getRequest(urlParams, StringResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanException(response);

        return new TokenBalance(address, new BigInteger(response.getResult()), contract);
    }

    @NotNull
    @Override
    public List<Balance> balances(final List<String> addresses) throws ApiException {
        if (BasicUtils.isEmpty(addresses))
            return Collections.emptyList();

        BasicUtils.validateAddresses(addresses);

        // Maximum addresses in batch request - 20
        final List<Balance> balances = new ArrayList<>();
        final List<List<String>> addressesAsBatches = BasicUtils.partition(addresses, 20);

        for (final List<String> batch : addressesAsBatches) {
            final String urlParams = ACT_BALANCE_MULTI_ACTION + TAG_LATEST_PARAM + ADDRESS_PARAM + toAddressParam(batch);
            final BalanceResponseTO response = getRequest(urlParams, BalanceResponseTO.class);
            if (response.getStatus() != 1)
                throw new EtherScanException(response);

            if (!BasicUtils.isEmpty(response.getResult()))
                balances.addAll(response.getResult().stream()
                        .map(Balance::of)
                        .collect(Collectors.toList()));
        }

        return balances;
    }

    private String toAddressParam(final List<String> addresses) {
        return addresses.stream().collect(Collectors.joining(","));
    }

    @NotNull
    @Override
    public List<Tx> txs(final String address) throws ApiException {
        return txs(address, MIN_START_BLOCK);
    }

    @NotNull
    @Override
    public List<Tx> txs(final String address, final long startBlock) throws ApiException {
        return txs(address, startBlock, MAX_END_BLOCK);
    }

    @NotNull
    @Override
    public List<Tx> txs(final String address, final long startBlock, final long endBlock) throws ApiException {
        BasicUtils.validateAddress(address);
        final BlockParam blocks = BasicUtils.compensateBlocks(startBlock, endBlock);

        final String offsetParam = PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX;
        final String blockParam = START_BLOCK_PARAM + blocks.start() + END_BLOCK_PARAM + blocks.end();
        final String urlParams = ACT_TX_ACTION + offsetParam + ADDRESS_PARAM + address + blockParam + SORT_ASC_PARAM;

        return getRequestUsingOffset(urlParams, TxResponseTO.class);
    }

    /**
     * Generic search for txs using offset api param To avoid 10k limit per response
     *
     * @param urlParams Url params for #getRequest()
     * @param tClass    responseListTO class
     * @param <T>       responseTO list T type
     * @param <R>       responseListTO type
     * @return List of T values
     */
    private <T, R extends BaseListResponseTO> List<T> getRequestUsingOffset(final String urlParams,
                                                                            Class<R> tClass) throws ApiException {
        final List<T> result = new ArrayList<>();
        int page = 1;
        while (true) {
            final String formattedUrl = String.format(urlParams, page++);
            final R response = getRequest(formattedUrl, tClass);
            BasicUtils.validateTxResponse(response);
            if (BasicUtils.isEmpty(response.getResult()))
                break;

            result.addAll(response.getResult());
            if (response.getResult().size() < OFFSET_MAX)
                break;
        }

        return result;
    }

    @NotNull
    @Override
    public List<TxInternal> txsInternal(final String address) throws ApiException {
        return txsInternal(address, MIN_START_BLOCK);
    }

    @NotNull
    @Override
    public List<TxInternal> txsInternal(final String address, final long startBlock) throws ApiException {
        return txsInternal(address, startBlock, MAX_END_BLOCK);
    }

    @NotNull
    @Override
    public List<TxInternal> txsInternal(final String address, final long startBlock, final long endBlock) throws ApiException {
        BasicUtils.validateAddress(address);
        final BlockParam blocks = BasicUtils.compensateBlocks(startBlock, endBlock);

        final String offsetParam = PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX;
        final String blockParam = START_BLOCK_PARAM + blocks.start() + END_BLOCK_PARAM + blocks.end();
        final String urlParams = ACT_TX_INTERNAL_ACTION + offsetParam + ADDRESS_PARAM + address + blockParam + SORT_ASC_PARAM;

        return getRequestUsingOffset(urlParams, TxInternalResponseTO.class);
    }

    @NotNull
    @Override
    public List<TxInternal> txsInternalByHash(final String txhash) throws ApiException {
        BasicUtils.validateTxHash(txhash);

        final String urlParams = ACT_TX_INTERNAL_ACTION + TXHASH_PARAM + txhash;
        final TxInternalResponseTO response = getRequest(urlParams, TxInternalResponseTO.class);
        BasicUtils.validateTxResponse(response);

        return BasicUtils.isEmpty(response.getResult())
                ? Collections.emptyList()
                : response.getResult();
    }

    @NotNull
    @Override
    public List<TxToken> txsToken(final String address) throws ApiException {
        return txsToken(address, MIN_START_BLOCK);
    }

    @NotNull
    @Override
    public List<TxToken> txsToken(final String address, final long startBlock) throws ApiException {
        return txsToken(address, startBlock, MAX_END_BLOCK);
    }

    @NotNull
    @Override
    public List<TxToken> txsToken(final String address, final long startBlock, final long endBlock) throws ApiException {
        BasicUtils.validateAddress(address);
        final BlockParam blocks = BasicUtils.compensateBlocks(startBlock, endBlock);

        final String offsetParam = PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX;
        final String blockParam = START_BLOCK_PARAM + blocks.start() + END_BLOCK_PARAM + blocks.end();
        final String urlParams = ACT_TX_TOKEN_ACTION + offsetParam + ADDRESS_PARAM + address + blockParam + SORT_ASC_PARAM;

        return getRequestUsingOffset(urlParams, TxTokenResponseTO.class);
    }

    @NotNull
    @Override
    public List<TxToken> txsNftToken(String address) throws ApiException {
        return txsNftToken(address, MIN_START_BLOCK);
    }

    @NotNull
    @Override
    public List<TxToken> txsNftToken(String address, long startBlock) throws ApiException {
        return txsNftToken(address, startBlock, MAX_END_BLOCK);
    }

    @NotNull
    @Override
    public List<TxToken> txsNftToken(String address, long startBlock, long endBlock) throws ApiException {
        BasicUtils.validateAddress(address);
        final BlockParam blocks = BasicUtils.compensateBlocks(startBlock, endBlock);

        final String offsetParam = PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX;
        final String blockParam = START_BLOCK_PARAM + blocks.start() + END_BLOCK_PARAM + blocks.end();
        final String urlParams = ACT_TX_NFT_TOKEN_ACTION + offsetParam + ADDRESS_PARAM + address + blockParam + SORT_ASC_PARAM;

        return getRequestUsingOffset(urlParams, TxTokenResponseTO.class);
    }

    @NotNull
    @Override
    public List<Block> minedBlocks(final String address) throws ApiException {
        BasicUtils.validateAddress(address);

        final String offsetParam = PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX;
        final String urlParams = ACT_MINED_ACTION + offsetParam + BLOCK_TYPE_PARAM + ADDRESS_PARAM + address;

        return getRequestUsingOffset(urlParams, BlockResponseTO.class);
    }
}
