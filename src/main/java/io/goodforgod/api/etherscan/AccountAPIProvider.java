package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.error.EtherScanResponseException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.model.*;
import io.goodforgod.api.etherscan.model.response.*;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

/**
 * Account API Implementation
 *
 * @see AccountAPI
 * @author GoodforGod
 * @since 28.10.2018
 */
final class AccountAPIProvider extends BasicProvider implements AccountAPI {

    private static final int OFFSET_MAX = 10000;

    private static final String ACT_BALANCE_ACTION = ACT_PREFIX + "balance";
    private static final String ACT_TOKEN_BALANCE_PARAM = ACT_PREFIX + "tokenbalance";
    private static final String ACT_BALANCE_MULTI_ACTION = ACT_PREFIX + "balancemulti";
    private static final String ACT_TX_ACTION = ACT_PREFIX + "txlist";
    private static final String ACT_TX_INTERNAL_ACTION = ACT_PREFIX + "txlistinternal";
    private static final String ACT_TX_ERC20_ACTION = ACT_PREFIX + "tokentx";
    private static final String ACT_TX_ERC721_ACTION = ACT_PREFIX + "tokennfttx";
    private static final String ACT_TX_ERC1155_ACTION = ACT_PREFIX + "token1155tx";
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

    AccountAPIProvider(RequestQueueManager requestQueueManager,
                       String baseUrl,
                       EthHttpClient executor,
                       Converter converter) {
        super(requestQueueManager, "account", baseUrl, executor, converter);
    }

    @NotNull
    @Override
    public Balance balance(@NotNull String address) throws EtherScanException {
        BasicUtils.validateAddress(address);

        final String urlParams = ACT_BALANCE_ACTION + TAG_LATEST_PARAM + ADDRESS_PARAM + address;
        final StringResponseTO response = getRequest(urlParams, StringResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanResponseException(response);

        return new Balance(address, Wei.ofWei(new BigInteger(response.getResult())));
    }

    @NotNull
    @Override
    public TokenBalance balance(@NotNull String address, @NotNull String contract) throws EtherScanException {
        BasicUtils.validateAddress(address);
        BasicUtils.validateAddress(contract);

        final String urlParams = ACT_TOKEN_BALANCE_PARAM + ADDRESS_PARAM + address + CONTRACT_PARAM + contract;
        final StringResponseTO response = getRequest(urlParams, StringResponseTO.class);
        if (response.getStatus() != 1)
            throw new EtherScanResponseException(response);

        return new TokenBalance(address, Wei.ofWei(new BigInteger(response.getResult())), contract);
    }

    @NotNull
    @Override
    public List<Balance> balances(@NotNull List<String> addresses) throws EtherScanException {
        if (BasicUtils.isEmpty(addresses)) {
            return Collections.emptyList();
        }

        BasicUtils.validateAddresses(addresses);

        // Maximum addresses in batch request - 20
        final List<Balance> balances = new ArrayList<>();
        final List<List<String>> addressesAsBatches = BasicUtils.partition(addresses, 20);

        for (final List<String> batch : addressesAsBatches) {
            final String urlParams = ACT_BALANCE_MULTI_ACTION + TAG_LATEST_PARAM + ADDRESS_PARAM
                    + BasicUtils.toAddressParam(batch);
            final BalanceResponseTO response = getRequest(urlParams, BalanceResponseTO.class);
            if (response.getStatus() != 1) {
                throw new EtherScanResponseException(response);
            }

            if (!BasicUtils.isEmpty(response.getResult())) {
                balances.addAll(response.getResult().stream()
                        .map(r -> new Balance(r.getAccount(), Wei.ofWei(new BigInteger(r.getBalance()))))
                        .collect(Collectors.toList()));
            }
        }

        return balances;
    }

    @NotNull
    @Override
    public List<Tx> txs(@NotNull String address) throws EtherScanException {
        return txs(address, MIN_START_BLOCK);
    }

    @NotNull
    @Override
    public List<Tx> txs(@NotNull String address, long startBlock) throws EtherScanException {
        return txs(address, startBlock, MAX_END_BLOCK);
    }

    @NotNull
    @Override
    public List<Tx> txs(@NotNull String address, long startBlock, long endBlock) throws EtherScanException {
        BasicUtils.validateAddress(address);
        final BlockParam blocks = BasicUtils.compensateBlocks(startBlock, endBlock);

        final String urlParams = ACT_TX_ACTION + PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX
                + ADDRESS_PARAM + address
                + START_BLOCK_PARAM + blocks.start() + END_BLOCK_PARAM + blocks.end()
                + SORT_ASC_PARAM;

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
    private <T, R extends BaseListResponseTO<T>> List<T> getRequestUsingOffset(final String urlParams, Class<R> tClass)
            throws EtherScanException {
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
    public List<TxInternal> txsInternal(@NotNull String address) throws EtherScanException {
        return txsInternal(address, MIN_START_BLOCK);
    }

    @NotNull
    @Override
    public List<TxInternal> txsInternal(@NotNull String address, long startBlock) throws EtherScanException {
        return txsInternal(address, startBlock, MAX_END_BLOCK);
    }

    @NotNull
    @Override
    public List<TxInternal> txsInternal(@NotNull String address, long startBlock, long endBlock)
            throws EtherScanException {
        BasicUtils.validateAddress(address);
        final BlockParam blocks = BasicUtils.compensateBlocks(startBlock, endBlock);

        final String urlParams = ACT_TX_INTERNAL_ACTION + PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX
                + ADDRESS_PARAM + address
                + START_BLOCK_PARAM + blocks.start() + END_BLOCK_PARAM + blocks.end()
                + SORT_ASC_PARAM;

        return getRequestUsingOffset(urlParams, TxInternalResponseTO.class);
    }

    @NotNull
    @Override
    public List<TxInternal> txsInternalByHash(@NotNull String txhash) throws EtherScanException {
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
    public List<TxErc20> txsErc20(@NotNull String address) throws EtherScanException {
        return txsErc20(address, MIN_START_BLOCK);
    }

    @NotNull
    @Override
    public List<TxErc20> txsErc20(@NotNull String address, long startBlock) throws EtherScanException {
        return txsErc20(address, startBlock, MAX_END_BLOCK);
    }

    @NotNull
    @Override
    public List<TxErc20> txsErc20(@NotNull String address, long startBlock, long endBlock) throws EtherScanException {
        BasicUtils.validateAddress(address);
        final BlockParam blocks = BasicUtils.compensateBlocks(startBlock, endBlock);

        final String urlParams = ACT_TX_ERC20_ACTION + PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX
                + ADDRESS_PARAM + address
                + START_BLOCK_PARAM + blocks.start() + END_BLOCK_PARAM + blocks.end()
                + SORT_ASC_PARAM;

        return getRequestUsingOffset(urlParams, TxErc20ResponseTO.class);
    }

    @NotNull
    @Override
    public List<TxErc20> txsErc20(@NotNull String address, @NotNull String contractAddress) throws EtherScanException {
        return txsErc20(address, contractAddress, MIN_START_BLOCK);
    }

    @NotNull
    @Override
    public List<TxErc20> txsErc20(@NotNull String address, @NotNull String contractAddress, long startBlock)
            throws EtherScanException {
        return txsErc20(address, contractAddress, startBlock, MAX_END_BLOCK);
    }

    @NotNull
    @Override
    public List<TxErc20> txsErc20(@NotNull String address, @NotNull String contractAddress, long startBlock, long endBlock)
            throws EtherScanException {
        BasicUtils.validateAddress(address);
        final BlockParam blocks = BasicUtils.compensateBlocks(startBlock, endBlock);

        final String offsetParam = PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX;
        final String blockParam = START_BLOCK_PARAM + blocks.start() + END_BLOCK_PARAM + blocks.end();
        final String urlParams = ACT_TX_ERC20_ACTION + offsetParam + ADDRESS_PARAM + address
                + CONTRACT_PARAM + contractAddress + blockParam + SORT_ASC_PARAM;

        return getRequestUsingOffset(urlParams, TxErc20ResponseTO.class);
    }

    @NotNull
    @Override
    public List<TxErc721> txsErc721(@NotNull String address) throws EtherScanException {
        return txsErc721(address, MIN_START_BLOCK);
    }

    @NotNull
    @Override
    public List<TxErc721> txsErc721(@NotNull String address, long startBlock) throws EtherScanException {
        return txsErc721(address, startBlock, MAX_END_BLOCK);
    }

    @NotNull
    @Override
    public List<TxErc721> txsErc721(@NotNull String address, long startBlock, long endBlock) throws EtherScanException {
        BasicUtils.validateAddress(address);
        final BlockParam blocks = BasicUtils.compensateBlocks(startBlock, endBlock);

        final String urlParams = ACT_TX_ERC721_ACTION + PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX
                + ADDRESS_PARAM + address
                + START_BLOCK_PARAM + blocks.start() + END_BLOCK_PARAM + blocks.end()
                + SORT_ASC_PARAM;

        return getRequestUsingOffset(urlParams, TxErc721ResponseTO.class);
    }

    @Override
    public @NotNull List<TxErc721>
            txsErc721(@NotNull String address, @NotNull String contractAddress, long startBlock, long endBlock)
                    throws EtherScanException {
        BasicUtils.validateAddress(address);
        final BlockParam blocks = BasicUtils.compensateBlocks(startBlock, endBlock);

        final String offsetParam = PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX;
        final String blockParam = START_BLOCK_PARAM + blocks.start() + END_BLOCK_PARAM + blocks.end();
        final String urlParams = ACT_TX_ERC721_ACTION + offsetParam + ADDRESS_PARAM + address
                + CONTRACT_PARAM + contractAddress + blockParam + SORT_ASC_PARAM;

        return getRequestUsingOffset(urlParams, TxErc721ResponseTO.class);
    }

    @Override
    public @NotNull List<TxErc721> txsErc721(@NotNull String address, @NotNull String contractAddress, long startBlock)
            throws EtherScanException {
        return txsErc721(address, contractAddress, startBlock, MAX_END_BLOCK);
    }

    @Override
    public @NotNull List<TxErc721> txsErc721(@NotNull String address, @NotNull String contractAddress) throws EtherScanException {
        return txsErc721(address, contractAddress, MIN_START_BLOCK);
    }

    @Override
    public @NotNull List<TxErc1155> txsErc1155(@NotNull String address, long startBlock, long endBlock)
            throws EtherScanException {
        BasicUtils.validateAddress(address);
        final BlockParam blocks = BasicUtils.compensateBlocks(startBlock, endBlock);

        final String urlParams = ACT_TX_ERC1155_ACTION + PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX
                + ADDRESS_PARAM + address
                + START_BLOCK_PARAM + blocks.start() + END_BLOCK_PARAM + blocks.end()
                + SORT_ASC_PARAM;

        return getRequestUsingOffset(urlParams, TxErc1155ResponseTO.class);
    }

    @Override
    public @NotNull List<TxErc1155> txsErc1155(@NotNull String address, long startBlock) throws EtherScanException {
        return txsErc1155(address, startBlock, MAX_END_BLOCK);
    }

    @Override
    public @NotNull List<TxErc1155> txsErc1155(@NotNull String address) throws EtherScanException {
        return txsErc1155(address, MIN_START_BLOCK);
    }

    @Override
    public @NotNull List<TxErc1155>
            txsErc1155(@NotNull String address, @NotNull String contractAddress, long startBlock, long endBlock)
                    throws EtherScanException {
        BasicUtils.validateAddress(address);
        final BlockParam blocks = BasicUtils.compensateBlocks(startBlock, endBlock);

        final String offsetParam = PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX;
        final String blockParam = START_BLOCK_PARAM + blocks.start() + END_BLOCK_PARAM + blocks.end();
        final String urlParams = ACT_TX_ERC1155_ACTION + offsetParam + ADDRESS_PARAM + address
                + CONTRACT_PARAM + contractAddress + blockParam + SORT_ASC_PARAM;

        return getRequestUsingOffset(urlParams, TxErc1155ResponseTO.class);
    }

    @Override
    public @NotNull List<TxErc1155> txsErc1155(@NotNull String address, @NotNull String contractAddress, long startBlock)
            throws EtherScanException {
        return txsErc1155(address, contractAddress, startBlock, MAX_END_BLOCK);
    }

    @Override
    public @NotNull List<TxErc1155> txsErc1155(@NotNull String address, @NotNull String contractAddress)
            throws EtherScanException {
        return txsErc1155(address, contractAddress, MIN_START_BLOCK);
    }

    @NotNull
    @Override
    public List<Block> blocksMined(@NotNull String address) throws EtherScanException {
        BasicUtils.validateAddress(address);

        final String urlParams = ACT_MINED_ACTION + PAGE_PARAM + "%s" + OFFSET_PARAM + OFFSET_MAX + BLOCK_TYPE_PARAM
                + ADDRESS_PARAM + address;

        return getRequestUsingOffset(urlParams, BlockResponseTO.class);
    }
}
