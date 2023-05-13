package io.goodforgod.api.etherscan.model;

import io.goodforgod.api.etherscan.util.BasicUtils;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 28.10.2018
 */
public class Tx extends BaseTx {

    private BigInteger value;
    private long nonce;
    private String blockHash;
    private int transactionIndex;
    private BigInteger gasPrice;
    private BigInteger cumulativeGasUsed;
    private long confirmations;
    private String isError;
    private String txreceipt_status;

    // <editor-fold desc="Getters">
    public BigInteger getValue() {
        return value;
    }

    public long getNonce() {
        return nonce;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public int getTransactionIndex() {
        return transactionIndex;
    }

    public BigInteger getGasPrice() {
        return gasPrice;
    }

    public boolean haveError() {
        return !BasicUtils.isEmpty(isError) && !isError.equals("0");
    }

    public String getTxreceipt_status() {
        return txreceipt_status;
    }

    public BigInteger getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public long getConfirmations() {
        return confirmations;
    }
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Tx))
            return false;
        if (!super.equals(o))
            return false;
        Tx tx = (Tx) o;
        return nonce == tx.nonce && transactionIndex == tx.transactionIndex && confirmations == tx.confirmations
                && Objects.equals(value, tx.value) && Objects.equals(blockHash, tx.blockHash)
                && Objects.equals(gasPrice, tx.gasPrice) && Objects.equals(cumulativeGasUsed, tx.cumulativeGasUsed)
                && Objects.equals(isError, tx.isError) && Objects.equals(txreceipt_status, tx.txreceipt_status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value, nonce, blockHash, transactionIndex, gasPrice, cumulativeGasUsed,
                confirmations, isError, txreceipt_status);
    }

    @Override
    public String toString() {
        return "Tx{" +
                "nonce=" + nonce +
                ", value='" + value + '\'' +
                ", blockHash='" + blockHash + '\'' +
                ", transactionIndex=" + transactionIndex +
                ", gasPrice=" + gasPrice +
                ", cumulativeGasUsed=" + cumulativeGasUsed +
                ", confirmations=" + confirmations +
                ", isError='" + isError + '\'' +
                ", txreceipt_status='" + txreceipt_status + '\'' +
                "} " + super.toString();
    }

    public static TxBuilder builder() {
        return new TxBuilder();
    }

    public static final class TxBuilder {

        private long blockNumber;
        private LocalDateTime timeStamp;
        private String hash;
        private String from;
        private String to;
        private BigInteger value;
        private String contractAddress;
        private String input;
        private BigInteger gas;
        private BigInteger gasUsed;
        private long nonce;
        private String blockHash;
        private int transactionIndex;
        private BigInteger gasPrice;
        private BigInteger cumulativeGasUsed;
        private long confirmations;
        private String isError;
        private String txreceiptStatus;

        private TxBuilder() {}

        public TxBuilder withBlockNumber(long blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public TxBuilder withTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public TxBuilder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public TxBuilder withFrom(String from) {
            this.from = from;
            return this;
        }

        public TxBuilder withTo(String to) {
            this.to = to;
            return this;
        }

        public TxBuilder withValue(BigInteger value) {
            this.value = value;
            return this;
        }

        public TxBuilder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public TxBuilder withInput(String input) {
            this.input = input;
            return this;
        }

        public TxBuilder withGas(BigInteger gas) {
            this.gas = gas;
            return this;
        }

        public TxBuilder withGasUsed(BigInteger gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public TxBuilder withNonce(long nonce) {
            this.nonce = nonce;
            return this;
        }

        public TxBuilder withBlockHash(String blockHash) {
            this.blockHash = blockHash;
            return this;
        }

        public TxBuilder withTransactionIndex(int transactionIndex) {
            this.transactionIndex = transactionIndex;
            return this;
        }

        public TxBuilder withGasPrice(BigInteger gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public TxBuilder withCumulativeGasUsed(BigInteger cumulativeGasUsed) {
            this.cumulativeGasUsed = cumulativeGasUsed;
            return this;
        }

        public TxBuilder withConfirmations(long confirmations) {
            this.confirmations = confirmations;
            return this;
        }

        public TxBuilder withIsError(String isError) {
            this.isError = isError;
            return this;
        }

        public TxBuilder withTxreceiptStatus(String txreceiptStatus) {
            this.txreceiptStatus = txreceiptStatus;
            return this;
        }

        public Tx build() {
            Tx tx = new Tx();
            tx.gas = this.gas;
            tx.isError = this.isError;
            tx.blockHash = this.blockHash;
            tx.hash = this.hash;
            tx.gasUsed = this.gasUsed;
            tx.from = this.from;
            tx.txreceipt_status = this.txreceiptStatus;
            tx.contractAddress = this.contractAddress;
            tx.value = this.value;
            tx.transactionIndex = this.transactionIndex;
            tx.confirmations = this.confirmations;
            tx.timeStamp = String.valueOf(this.timeStamp.toEpochSecond(ZoneOffset.UTC));
            tx.nonce = this.nonce;
            tx.blockNumber = this.blockNumber;
            tx._timeStamp = this.timeStamp;
            tx.to = this.to;
            tx.input = this.input;
            tx.cumulativeGasUsed = this.cumulativeGasUsed;
            tx.gasPrice = this.gasPrice;
            return tx;
        }
    }
}
