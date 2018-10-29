package io.api.core.impl;

import com.sun.istack.internal.NotNull;
import io.api.core.IAccountProvider;
import io.api.model.Balance;
import io.api.model.Block;
import io.api.model.Transaction;

import java.util.List;
import java.util.Map;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class AccountProvider extends BasicProvider implements IAccountProvider {

    private static final int MAX_END_BLOCK = 999999999;
    private static final int MIN_START_BLOCK = 0;

    private static final String BLOCK_TYPE_PARAM = "&blocktype=blocks";
    private static final String START_BLOCK_PARAM = "&endblock=";
    private static final String END_BLOCK_PARAM = "&startblock=";
    private static final String SORT_DESC_PARAM = "&sort=desc";
    private static final String SORT_ASC_PARAM = "&sort=asc";
    private static final String ADDRESS_PARAM = "&address=";
    private static final String OFFSET_PARAM = "&offset=";
    private static final String PAGE_PARAM = "&page=";

    public AccountProvider(final String baseUrl,
                           final Map<String, String> header) {
        super("account", baseUrl, header);
    }

    @NotNull
    @Override
    public Balance balance(final String address) {
        return null;
    }

    @NotNull
    @Override
    public List<Balance> balances(final List<String> addresses) {
        return null;
    }

    @NotNull
    @Override
    public List<Transaction> txs(final String address) {
        return null;
    }

    @NotNull
    @Override
    public List<Transaction> txs(final String address, final int startBlock) {
        return txs(address, startBlock, MAX_END_BLOCK);
    }

    @NotNull
    @Override
    public List<Transaction> txs(final String address, final int startBlock, final int endBlock) {
        return null;
    }

    @NotNull
    @Override
    public List<Transaction> txsInternal(final String address) {
        return null;
    }

    @NotNull
    @Override
    public List<Transaction> txsInternal(final String address, final int startBlock) {
        return txsInternal(address, startBlock, MAX_END_BLOCK);
    }

    @NotNull
    @Override
    public List<Transaction> txsInternal(final String address, final int startBlock, final int endBlock) {
        return null;
    }

    @NotNull
    @Override
    public List<Transaction> txsToken(final String address) {
        return null;
    }

    @NotNull
    @Override
    public List<Transaction> txsToken(final String address, final int startBlock) {
        return txsToken(address, startBlock, MAX_END_BLOCK);
    }

    @NotNull
    @Override
    public List<Transaction> txsToken(final String address, final int startBlock, final int endBlock) {
        return null;
    }

    @NotNull
    @Override
    public List<Block> minedBlocks(final String address) {
        return null;
    }
}
