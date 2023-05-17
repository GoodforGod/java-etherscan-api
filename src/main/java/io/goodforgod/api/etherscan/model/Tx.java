package io.goodforgod.api.etherscan.model;

import io.goodforgod.api.etherscan.util.BasicUtils;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author GoodforGod
 * @since 28.10.2018
 */
public class Tx extends BlockTx {

    private BigInteger value;
    private String isError;
    private String txreceipt_status;

    protected Tx() {}

    // <editor-fold desc="Getters">
    public BigInteger getValue() {
        return value;
    }

    public boolean haveError() {
        return !BasicUtils.isEmpty(isError) && !isError.equals("0");
    }

    public String getTxReceiptStatus() {
        return txreceipt_status;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "Tx{" +
                "value=" + value +
                ", isError='" + isError + '\'' +
                ", txreceipt_status='" + txreceipt_status + '\'' +
                ", nonce=" + nonce +
                ", blockHash='" + blockHash + '\'' +
                ", transactionIndex=" + transactionIndex +
                ", confirmations=" + confirmations +
                ", gasPrice=" + gasPrice +
                ", cumulativeGasUsed=" + cumulativeGasUsed +
                ", blockNumber=" + blockNumber +
                ", timeStamp='" + timeStamp + '\'' +
                ", hash='" + hash + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", contractAddress='" + contractAddress + '\'' +
                ", input='" + input + '\'' +
                ", gas=" + gas +
                ", gasUsed=" + gasUsed +
                '}';
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
        private Wei gas;
        private Wei gasUsed;
        private long nonce;
        private String blockHash;
        private int transactionIndex;
        private Wei gasPrice;
        private Wei cumulativeGasUsed;
        private long confirmations;
        private String isError;
        private String txReceiptStatus;

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

        public TxBuilder withGas(Wei gas) {
            this.gas = gas;
            return this;
        }

        public TxBuilder withGasUsed(Wei gasUsed) {
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

        public TxBuilder withGasPrice(Wei gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public TxBuilder withCumulativeGasUsed(Wei cumulativeGasUsed) {
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

        public TxBuilder withTxReceiptStatus(String txReceiptStatus) {
            this.txReceiptStatus = txReceiptStatus;
            return this;
        }

        public Tx build() {
            Tx tx = new Tx();
            tx.isError = this.isError;
            tx.blockHash = this.blockHash;
            tx.hash = this.hash;
            if (this.gas != null) {
                tx.gas = this.gas.asWei();
            }
            if (this.gasUsed != null) {
                tx.gasUsed = this.gasUsed.asWei();
            }
            if (this.gasPrice != null) {
                tx.gasPrice = this.gasPrice.asWei();
            }
            if (this.cumulativeGasUsed != null) {
                tx.cumulativeGasUsed = this.cumulativeGasUsed.asWei();
            }
            tx.from = this.from;
            tx.txreceipt_status = this.txReceiptStatus;
            tx.contractAddress = this.contractAddress;
            tx.value = this.value;
            tx.transactionIndex = this.transactionIndex;
            tx.confirmations = this.confirmations;
            if (this.timeStamp != null) {
                tx.timeStamp = String.valueOf(this.timeStamp.toEpochSecond(ZoneOffset.UTC));
                tx._timeStamp = this.timeStamp;
            }
            tx.nonce = this.nonce;
            tx.blockNumber = this.blockNumber;
            tx.to = this.to;
            tx.input = this.input;
            return tx;
        }
    }
}
