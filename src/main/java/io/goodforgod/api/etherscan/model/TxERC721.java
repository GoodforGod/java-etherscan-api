package io.goodforgod.api.etherscan.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author GoodforGod
 * @since 28.10.2018
 */
public class TxERC721 extends BaseTx {

    private long nonce;
    private String blockHash;
    private String tokenName;
    private String tokenSymbol;
    private String tokenDecimal;
    private int transactionIndex;
    private long gasPrice;
    private long cumulativeGasUsed;
    private long confirmations;

    // <editor-fold desc="Getters">
    public long getNonce() {
        return nonce;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getTokenName() {
        return tokenName;
    }

    public String getTokenSymbol() {
        return tokenSymbol;
    }

    public String getTokenDecimal() {
        return tokenDecimal;
    }

    public int getTransactionIndex() {
        return transactionIndex;
    }

    public long getGasPrice() {
        return gasPrice;
    }

    public long getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public long getConfirmations() {
        return confirmations;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "TxERC721{" +
                "nonce=" + nonce +
                ", blockHash='" + blockHash + '\'' +
                ", tokenName='" + tokenName + '\'' +
                ", tokenSymbol='" + tokenSymbol + '\'' +
                ", tokenDecimal='" + tokenDecimal + '\'' +
                ", transactionIndex=" + transactionIndex +
                ", gasPrice=" + gasPrice +
                ", cumulativeGasUsed=" + cumulativeGasUsed +
                ", confirmations=" + confirmations +
                "} " + super.toString();
    }

    public static TxERC721Builder builder() {
        return new TxERC721Builder();
    }

    public static final class TxERC721Builder {

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
        private String tokenName;
        private String tokenSymbol;
        private String tokenDecimal;
        private int transactionIndex;
        private long gasPrice;
        private long cumulativeGasUsed;
        private long confirmations;

        private TxERC721Builder() {}

        public TxERC721Builder withBlockNumber(long blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public TxERC721Builder withTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public TxERC721Builder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public TxERC721Builder withFrom(String from) {
            this.from = from;
            return this;
        }

        public TxERC721Builder withTo(String to) {
            this.to = to;
            return this;
        }

        public TxERC721Builder withValue(BigInteger value) {
            this.value = value;
            return this;
        }

        public TxERC721Builder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public TxERC721Builder withInput(String input) {
            this.input = input;
            return this;
        }

        public TxERC721Builder withGas(BigInteger gas) {
            this.gas = gas;
            return this;
        }

        public TxERC721Builder withGasUsed(BigInteger gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public TxERC721Builder withNonce(long nonce) {
            this.nonce = nonce;
            return this;
        }

        public TxERC721Builder withBlockHash(String blockHash) {
            this.blockHash = blockHash;
            return this;
        }

        public TxERC721Builder withTokenName(String tokenName) {
            this.tokenName = tokenName;
            return this;
        }

        public TxERC721Builder withTokenSymbol(String tokenSymbol) {
            this.tokenSymbol = tokenSymbol;
            return this;
        }

        public TxERC721Builder withTokenDecimal(String tokenDecimal) {
            this.tokenDecimal = tokenDecimal;
            return this;
        }

        public TxERC721Builder withTransactionIndex(int transactionIndex) {
            this.transactionIndex = transactionIndex;
            return this;
        }

        public TxERC721Builder withGasPrice(long gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public TxERC721Builder withCumulativeGasUsed(long cumulativeGasUsed) {
            this.cumulativeGasUsed = cumulativeGasUsed;
            return this;
        }

        public TxERC721Builder withConfirmations(long confirmations) {
            this.confirmations = confirmations;
            return this;
        }

        public TxERC721 build() {
            TxERC721 txERC721 = new TxERC721();
            txERC721.gas = this.gas;
            txERC721.tokenName = this.tokenName;
            txERC721.hash = this.hash;
            txERC721.gasUsed = this.gasUsed;
            txERC721.nonce = this.nonce;
            txERC721.from = this.from;
            txERC721.gasPrice = this.gasPrice;
            txERC721.contractAddress = this.contractAddress;
            txERC721.cumulativeGasUsed = this.cumulativeGasUsed;
            txERC721.value = this.value;
            txERC721.timeStamp = String.valueOf(this.timeStamp.toEpochSecond(ZoneOffset.UTC));
            txERC721.blockNumber = this.blockNumber;
            txERC721._timeStamp = this.timeStamp;
            txERC721.tokenDecimal = this.tokenDecimal;
            txERC721.transactionIndex = this.transactionIndex;
            txERC721.to = this.to;
            txERC721.confirmations = this.confirmations;
            txERC721.input = this.input;
            txERC721.blockHash = this.blockHash;
            txERC721.tokenSymbol = this.tokenSymbol;
            return txERC721;
        }
    }
}
