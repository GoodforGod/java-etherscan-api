package io.goodforgod.api.etherscan.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 28.10.2018
 */
public class TxErc1155 extends BaseTx {

    private long nonce;
    private String blockHash;
    private String tokenID;
    private String tokenName;
    private String tokenSymbol;
    private String tokenValue;
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

    public String getTokenID() {
        return tokenID;
    }

    public String getTokenName() {
        return tokenName;
    }

    public String getTokenSymbol() {
        return tokenSymbol;
    }

    public String getTokenValue() {
        return tokenValue;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TxErc1155))
            return false;
        if (!super.equals(o))
            return false;
        TxErc1155 txErc1155 = (TxErc1155) o;
        return Objects.equals(tokenID, txErc1155.tokenID) && Objects.equals(tokenName, txErc1155.tokenName)
                && Objects.equals(tokenSymbol, txErc1155.tokenSymbol) && Objects.equals(tokenValue, txErc1155.tokenValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tokenID, tokenName, tokenSymbol, tokenValue);
    }

    @Override
    public String toString() {
        return "TxERC721{" +
                "nonce=" + nonce +
                ", blockHash='" + blockHash + '\'' +
                ", tokenID='" + tokenID + '\'' +
                ", tokenName='" + tokenName + '\'' +
                ", tokenSymbol='" + tokenSymbol + '\'' +
                ", tokenValue='" + tokenValue + '\'' +
                ", transactionIndex=" + transactionIndex +
                ", gasPrice=" + gasPrice +
                ", cumulativeGasUsed=" + cumulativeGasUsed +
                ", confirmations=" + confirmations +
                "} " + super.toString();
    }

    public static TxErc1155Builder builder() {
        return new TxErc1155Builder();
    }

    public static final class TxErc1155Builder {

        private long blockNumber;
        private LocalDateTime timeStamp;
        private String hash;
        private String from;
        private String to;
        private String contractAddress;
        private String input;
        private BigInteger gas;
        private BigInteger gasUsed;
        private long nonce;
        private String blockHash;
        private String tokenID;
        private String tokenName;
        private String tokenSymbol;
        private String tokenValue;
        private int transactionIndex;
        private long gasPrice;
        private long cumulativeGasUsed;
        private long confirmations;

        private TxErc1155Builder() {}

        public TxErc1155Builder withBlockNumber(long blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public TxErc1155Builder withTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public TxErc1155Builder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public TxErc1155Builder withFrom(String from) {
            this.from = from;
            return this;
        }

        public TxErc1155Builder withTo(String to) {
            this.to = to;
            return this;
        }

        public TxErc1155Builder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public TxErc1155Builder withInput(String input) {
            this.input = input;
            return this;
        }

        public TxErc1155Builder withGas(BigInteger gas) {
            this.gas = gas;
            return this;
        }

        public TxErc1155Builder withGasUsed(BigInteger gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public TxErc1155Builder withNonce(long nonce) {
            this.nonce = nonce;
            return this;
        }

        public TxErc1155Builder withBlockHash(String blockHash) {
            this.blockHash = blockHash;
            return this;
        }

        public TxErc1155Builder withTokenID(String tokenID) {
            this.tokenID = tokenID;
            return this;
        }

        public TxErc1155Builder withTokenName(String tokenName) {
            this.tokenName = tokenName;
            return this;
        }

        public TxErc1155Builder withTokenSymbol(String tokenSymbol) {
            this.tokenSymbol = tokenSymbol;
            return this;
        }

        public TxErc1155Builder withTokenDecimal(String tokenDecimal) {
            this.tokenValue = tokenDecimal;
            return this;
        }

        public TxErc1155Builder withTransactionIndex(int transactionIndex) {
            this.transactionIndex = transactionIndex;
            return this;
        }

        public TxErc1155Builder withGasPrice(long gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public TxErc1155Builder withCumulativeGasUsed(long cumulativeGasUsed) {
            this.cumulativeGasUsed = cumulativeGasUsed;
            return this;
        }

        public TxErc1155Builder withConfirmations(long confirmations) {
            this.confirmations = confirmations;
            return this;
        }

        public TxErc1155 build() {
            TxErc1155 txERC721 = new TxErc1155();
            txERC721.gas = this.gas;
            txERC721.tokenName = this.tokenName;
            txERC721.hash = this.hash;
            txERC721.gasUsed = this.gasUsed;
            txERC721.nonce = this.nonce;
            txERC721.from = this.from;
            txERC721.gasPrice = this.gasPrice;
            txERC721.contractAddress = this.contractAddress;
            txERC721.cumulativeGasUsed = this.cumulativeGasUsed;
            txERC721.tokenID = this.tokenID;
            txERC721.timeStamp = String.valueOf(this.timeStamp.toEpochSecond(ZoneOffset.UTC));
            txERC721.blockNumber = this.blockNumber;
            txERC721._timeStamp = this.timeStamp;
            txERC721.tokenValue = this.tokenValue;
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
