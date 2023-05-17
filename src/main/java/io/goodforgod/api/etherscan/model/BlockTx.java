package io.goodforgod.api.etherscan.model;

import java.math.BigInteger;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 15.05.2023
 */
abstract class BlockTx extends BaseTx {

    long nonce;
    String blockHash;
    long transactionIndex;
    long confirmations;
    BigInteger gasPrice;
    BigInteger cumulativeGasUsed;

    public long getNonce() {
        return nonce;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public long getTransactionIndex() {
        return transactionIndex;
    }

    public Wei getGasPrice() {
        return Wei.ofWei(gasPrice);
    }

    public Wei getGasUsedCumulative() {
        return Wei.ofWei(cumulativeGasUsed);
    }

    public long getConfirmations() {
        return confirmations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BlockTx))
            return false;
        if (!super.equals(o))
            return false;
        BlockTx blockTx = (BlockTx) o;
        return nonce == blockTx.nonce && transactionIndex == blockTx.transactionIndex
                && Objects.equals(blockHash, blockTx.blockHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nonce, blockHash, transactionIndex);
    }

    @Override
    public int compareTo(@NotNull BaseTx o) {
        final int superCompare = super.compareTo(o);
        if (superCompare == 0) {
            if (o instanceof Tx) {
                return Long.compare(transactionIndex, ((Tx) o).getTransactionIndex());
            } else if (o instanceof TxErc20) {
                return Long.compare(transactionIndex, ((TxErc20) o).getTransactionIndex());
            } else if (o instanceof TxErc721) {
                return Long.compare(transactionIndex, ((TxErc721) o).getTransactionIndex());
            } else if (o instanceof TxErc1155) {
                return Long.compare(transactionIndex, ((TxErc1155) o).getTransactionIndex());
            }
        }

        return superCompare;
    }
}
