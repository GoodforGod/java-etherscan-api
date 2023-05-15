package io.goodforgod.api.etherscan.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 28.10.2018
 */
public class TxErc1155 extends BlockTx {

    private String tokenID;
    private String tokenName;
    private String tokenSymbol;
    private String tokenValue;

    protected TxErc1155() {}

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

    public String getTokenValue() {
        return tokenValue;
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
        return "TxErc1155{" +
                "tokenID='" + tokenID + '\'' +
                ", tokenName='" + tokenName + '\'' +
                ", tokenSymbol='" + tokenSymbol + '\'' +
                ", tokenValue='" + tokenValue + '\'' +
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
        private long nonce;
        private String blockHash;
        private String tokenID;
        private String tokenName;
        private String tokenSymbol;
        private String tokenValue;
        private int transactionIndex;
        private Wei gas;
        private Wei gasUsed;
        private Wei gasPrice;
        private Wei cumulativeGasUsed;
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

        public TxErc1155Builder withGas(Wei gas) {
            this.gas = gas;
            return this;
        }

        public TxErc1155Builder withGasUsed(Wei gasUsed) {
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

        public TxErc1155Builder withGasPrice(Wei gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public TxErc1155Builder withCumulativeGasUsed(Wei cumulativeGasUsed) {
            this.cumulativeGasUsed = cumulativeGasUsed;
            return this;
        }

        public TxErc1155Builder withConfirmations(long confirmations) {
            this.confirmations = confirmations;
            return this;
        }

        public TxErc1155 build() {
            TxErc1155 txERC1155 = new TxErc1155();
            txERC1155.tokenName = this.tokenName;
            txERC1155.hash = this.hash;
            txERC1155.nonce = this.nonce;
            txERC1155.from = this.from;
            if (this.gas != null) {
                txERC1155.gas = this.gas.asWei();
            }
            if (this.gasUsed != null) {
                txERC1155.gasUsed = this.gasUsed.asWei();
            }
            if (this.gasPrice != null) {
                txERC1155.gasPrice = this.gasPrice.asWei();
            }
            if (this.cumulativeGasUsed != null) {
                txERC1155.cumulativeGasUsed = this.cumulativeGasUsed.asWei();
            }
            txERC1155.contractAddress = this.contractAddress;
            txERC1155.tokenID = this.tokenID;
            if (this.timeStamp != null) {
                txERC1155.timeStamp = String.valueOf(this.timeStamp.toEpochSecond(ZoneOffset.UTC));
                txERC1155._timeStamp = this.timeStamp;
            }
            txERC1155.blockNumber = this.blockNumber;
            txERC1155.tokenValue = this.tokenValue;
            txERC1155.transactionIndex = this.transactionIndex;
            txERC1155.to = this.to;
            txERC1155.confirmations = this.confirmations;
            txERC1155.input = this.input;
            txERC1155.blockHash = this.blockHash;
            txERC1155.tokenSymbol = this.tokenSymbol;
            return txERC1155;
        }
    }
}
