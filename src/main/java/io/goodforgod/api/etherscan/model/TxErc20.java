package io.goodforgod.api.etherscan.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 28.10.2018
 */
public class TxErc20 extends BaseTx {

    private BigInteger value;
    private long nonce;
    private String blockHash;
    private String tokenName;
    private String tokenSymbol;
    private String tokenDecimal;
    private int transactionIndex;
    private BigInteger gasPrice;
    private BigInteger cumulativeGasUsed;
    private long confirmations;

    protected TxErc20() {}

    // <editor-fold desc="Getters">
    public long getNonce() {
        return nonce;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public BigInteger getValue() {
        return value;
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

    public BigInteger getGasPrice() {
        return gasPrice;
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
        if (!(o instanceof TxErc20))
            return false;
        if (!super.equals(o))
            return false;
        TxErc20 txErc20 = (TxErc20) o;
        return Objects.equals(tokenName, txErc20.tokenName) && Objects.equals(tokenSymbol, txErc20.tokenSymbol)
                && Objects.equals(tokenDecimal, txErc20.tokenDecimal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tokenName, tokenSymbol, tokenDecimal);
    }

    @Override
    public String toString() {
        return "TxERC20{" +
                "nonce=" + nonce +
                ", blockHash='" + blockHash + '\'' +
                ", value='" + value + '\'' +
                ", tokenName='" + tokenName + '\'' +
                ", tokenSymbol='" + tokenSymbol + '\'' +
                ", tokenDecimal='" + tokenDecimal + '\'' +
                ", transactionIndex=" + transactionIndex +
                ", gasPrice=" + gasPrice +
                ", cumulativeGasUsed=" + cumulativeGasUsed +
                ", confirmations=" + confirmations +
                "} " + super.toString();
    }

    public static TxERC20Builder builder() {
        return new TxERC20Builder();
    }

    public static final class TxERC20Builder {

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
        private BigInteger gasPrice;
        private BigInteger cumulativeGasUsed;
        private long confirmations;

        private TxERC20Builder() {}

        public TxERC20Builder withBlockNumber(long blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public TxERC20Builder withTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public TxERC20Builder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public TxERC20Builder withFrom(String from) {
            this.from = from;
            return this;
        }

        public TxERC20Builder withTo(String to) {
            this.to = to;
            return this;
        }

        public TxERC20Builder withValue(BigInteger value) {
            this.value = value;
            return this;
        }

        public TxERC20Builder withContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public TxERC20Builder withInput(String input) {
            this.input = input;
            return this;
        }

        public TxERC20Builder withGas(BigInteger gas) {
            this.gas = gas;
            return this;
        }

        public TxERC20Builder withGasUsed(BigInteger gasUsed) {
            this.gasUsed = gasUsed;
            return this;
        }

        public TxERC20Builder withNonce(long nonce) {
            this.nonce = nonce;
            return this;
        }

        public TxERC20Builder withBlockHash(String blockHash) {
            this.blockHash = blockHash;
            return this;
        }

        public TxERC20Builder withTokenName(String tokenName) {
            this.tokenName = tokenName;
            return this;
        }

        public TxERC20Builder withTokenSymbol(String tokenSymbol) {
            this.tokenSymbol = tokenSymbol;
            return this;
        }

        public TxERC20Builder withTokenDecimal(String tokenDecimal) {
            this.tokenDecimal = tokenDecimal;
            return this;
        }

        public TxERC20Builder withTransactionIndex(int transactionIndex) {
            this.transactionIndex = transactionIndex;
            return this;
        }

        public TxERC20Builder withGasPrice(BigInteger gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public TxERC20Builder withCumulativeGasUsed(BigInteger cumulativeGasUsed) {
            this.cumulativeGasUsed = cumulativeGasUsed;
            return this;
        }

        public TxERC20Builder withConfirmations(long confirmations) {
            this.confirmations = confirmations;
            return this;
        }

        public TxErc20 build() {
            TxErc20 txERC20 = new TxErc20();
            txERC20.gas = this.gas;
            txERC20.tokenName = this.tokenName;
            txERC20.hash = this.hash;
            txERC20.gasUsed = this.gasUsed;
            txERC20.cumulativeGasUsed = this.cumulativeGasUsed;
            txERC20.from = this.from;
            txERC20.tokenSymbol = this.tokenSymbol;
            txERC20.transactionIndex = this.transactionIndex;
            txERC20.contractAddress = this.contractAddress;
            txERC20.nonce = this.nonce;
            txERC20.confirmations = this.confirmations;
            txERC20.value = this.value;
            txERC20.timeStamp = String.valueOf(this.timeStamp.toEpochSecond(ZoneOffset.UTC));
            txERC20.blockHash = this.blockHash;
            txERC20.blockNumber = this.blockNumber;
            txERC20._timeStamp = this.timeStamp;
            txERC20.gasPrice = this.gasPrice;
            txERC20.to = this.to;
            txERC20.input = this.input;
            txERC20.tokenDecimal = this.tokenDecimal;
            return txERC20;
        }
    }
}
