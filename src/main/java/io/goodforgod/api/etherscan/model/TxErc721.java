package io.goodforgod.api.etherscan.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 28.10.2018
 */
public class TxErc721 extends BlockTx {

    private String tokenID;
    private String tokenName;
    private String tokenSymbol;
    private String tokenDecimal;

    protected TxErc721() {}

    // <editor-fold desc="Getters">
    public String getTokenID() {
        return tokenID;
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
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TxErc721))
            return false;
        if (!super.equals(o))
            return false;
        TxErc721 txErc721 = (TxErc721) o;
        return Objects.equals(tokenID, txErc721.tokenID) && Objects.equals(tokenName, txErc721.tokenName)
                && Objects.equals(tokenSymbol, txErc721.tokenSymbol) && Objects.equals(tokenDecimal, txErc721.tokenDecimal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tokenID, tokenName, tokenSymbol, tokenDecimal);
    }

    @Override
    public String toString() {
        return "TxErc721{" +
                "tokenID=" + tokenID +
                ", tokenName=" + tokenName +
                ", tokenSymbol=" + tokenSymbol +
                ", tokenDecimal=" + tokenDecimal +
                ", nonce=" + nonce +
                ", blockHash=" + blockHash +
                ", transactionIndex=" + transactionIndex +
                ", confirmations=" + confirmations +
                ", gasPrice=" + gasPrice +
                ", cumulativeGasUsed=" + cumulativeGasUsed +
                ", blockNumber=" + blockNumber +
                ", timeStamp=" + timeStamp +
                ", hash=" + hash +
                ", from=" + from +
                ", to=" + to +
                ", contractAddress=" + contractAddress +
                ", input=" + input +
                ", gas=" + gas +
                ", gasUsed=" + gasUsed +
                '}';
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
        private String contractAddress;
        private String input;
        private long nonce;
        private String blockHash;
        private String tokenID;
        private String tokenName;
        private String tokenSymbol;
        private String tokenDecimal;
        private int transactionIndex;
        private Wei gas;
        private Wei gasUsed;
        private Wei gasPrice;
        private Wei cumulativeGasUsed;
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

        public TxERC721Builder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public TxERC721Builder withInput(String input) {
            this.input = input;
            return this;
        }

        public TxERC721Builder withGas(Wei gas) {
            this.gas = gas;
            return this;
        }

        public TxERC721Builder withGasUsed(Wei gasUsed) {
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

        public TxERC721Builder withTokenID(String tokenID) {
            this.tokenID = tokenID;
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

        public TxERC721Builder withGasPrice(Wei gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public TxERC721Builder withCumulativeGasUsed(Wei cumulativeGasUsed) {
            this.cumulativeGasUsed = cumulativeGasUsed;
            return this;
        }

        public TxERC721Builder withConfirmations(long confirmations) {
            this.confirmations = confirmations;
            return this;
        }

        public TxErc721 build() {
            TxErc721 txERC721 = new TxErc721();
            txERC721.tokenName = this.tokenName;
            txERC721.hash = this.hash;
            txERC721.nonce = this.nonce;
            txERC721.from = this.from;
            if (this.gas != null) {
                txERC721.gas = this.gas.asWei();
            }
            if (this.gasUsed != null) {
                txERC721.gasUsed = this.gasUsed.asWei();
            }
            if (this.gasPrice != null) {
                txERC721.gasPrice = this.gasPrice.asWei();
            }
            if (this.cumulativeGasUsed != null) {
                txERC721.cumulativeGasUsed = this.cumulativeGasUsed.asWei();
            }
            txERC721.contractAddress = this.contractAddress;
            txERC721.tokenID = this.tokenID;
            if (this.timeStamp != null) {
                txERC721.timeStamp = String.valueOf(this.timeStamp.toEpochSecond(ZoneOffset.UTC));
                txERC721._timeStamp = this.timeStamp;
            }
            txERC721.blockNumber = this.blockNumber;
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
