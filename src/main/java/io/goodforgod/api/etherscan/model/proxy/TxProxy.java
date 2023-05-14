package io.goodforgod.api.etherscan.model.proxy;

import com.google.gson.annotations.Expose;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.math.BigInteger;

/**
 * @author GoodforGod
 * @since 31.10.2018
 */
public class TxProxy {

    private String to;
    private String hash;
    private String transactionIndex;
    @Expose(deserialize = false, serialize = false)
    private Long _transactionIndex;
    private String from;
    private String v;
    private String input;
    private String s;
    private String r;
    private String nonce;
    @Expose(deserialize = false, serialize = false)
    private Long _nonce;
    private String value;
    private String gas;
    @Expose(deserialize = false, serialize = false)
    private BigInteger _gas;
    private String gasPrice;
    @Expose(deserialize = false, serialize = false)
    private BigInteger _gasPrice;
    private String blockHash;
    private String blockNumber;
    @Expose(deserialize = false, serialize = false)
    private Long _blockNumber;

    protected TxProxy() {}

    // <editor-fold desc="Getters">
    public String getTo() {
        return to;
    }

    public String getHash() {
        return hash;
    }

    public Long getTransactionIndex() {
        if (_transactionIndex == null && !BasicUtils.isEmpty(transactionIndex))
            _transactionIndex = BasicUtils.parseHex(transactionIndex).longValue();
        return _transactionIndex;
    }

    public String getFrom() {
        return from;
    }

    public BigInteger getGas() {
        if (_gas == null && !BasicUtils.isEmpty(gas))
            _gas = BasicUtils.parseHex(gas);
        return _gas;
    }

    public String getV() {
        return v;
    }

    public String getInput() {
        return input;
    }

    public String getS() {
        return s;
    }

    public String getR() {
        return r;
    }

    public Long getNonce() {
        if (_nonce == null && !BasicUtils.isEmpty(nonce))
            _nonce = BasicUtils.parseHex(nonce).longValue();
        return _nonce;
    }

    public String getValue() {
        return value;
    }

    public BigInteger getGasPrice() {
        if (_gasPrice == null && !BasicUtils.isEmpty(gasPrice))
            _gasPrice = BasicUtils.parseHex(gasPrice);
        return _gasPrice;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public Long getBlockNumber() {
        if (_blockNumber == null && !BasicUtils.isEmpty(blockNumber))
            _blockNumber = BasicUtils.parseHex(blockNumber).longValue();
        return _blockNumber;
    }
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TxProxy txProxy = (TxProxy) o;

        if (hash != null
                ? !hash.equals(txProxy.hash)
                : txProxy.hash != null)
            return false;
        if (blockHash != null
                ? !blockHash.equals(txProxy.blockHash)
                : txProxy.blockHash != null)
            return false;
        return blockNumber != null
                ? blockNumber.equals(txProxy.blockNumber)
                : txProxy.blockNumber == null;
    }

    @Override
    public int hashCode() {
        int result = hash != null
                ? hash.hashCode()
                : 0;
        result = 31 * result + (blockHash != null
                ? blockHash.hashCode()
                : 0);
        result = 31 * result + (blockNumber != null
                ? blockNumber.hashCode()
                : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TxProxy{" +
                "to='" + to + '\'' +
                ", hash='" + hash + '\'' +
                ", transactionIndex='" + transactionIndex + '\'' +
                ", _transactionIndex=" + _transactionIndex +
                ", from='" + from + '\'' +
                ", v='" + v + '\'' +
                ", input='" + input + '\'' +
                ", s='" + s + '\'' +
                ", r='" + r + '\'' +
                ", nonce='" + nonce + '\'' +
                ", _nonce=" + _nonce +
                ", value='" + value + '\'' +
                ", gas='" + gas + '\'' +
                ", _gas=" + _gas +
                ", gasPrice='" + gasPrice + '\'' +
                ", _gasPrice=" + _gasPrice +
                ", blockHash='" + blockHash + '\'' +
                ", blockNumber='" + blockNumber + '\'' +
                ", _blockNumber=" + _blockNumber +
                '}';
    }

    public static TxProxyBuilder builder() {
        return new TxProxyBuilder();
    }

    public static final class TxProxyBuilder {

        private String to;
        private String hash;
        private Long transactionIndex;
        private String from;
        private String v;
        private String input;
        private String s;
        private String r;
        private Long nonce;
        private String value;
        private BigInteger gas;
        private BigInteger gasPrice;
        private String blockHash;
        private Long blockNumber;

        private TxProxyBuilder() {}

        public TxProxyBuilder withTo(String to) {
            this.to = to;
            return this;
        }

        public TxProxyBuilder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public TxProxyBuilder withTransactionIndex(Long transactionIndex) {
            this.transactionIndex = transactionIndex;
            return this;
        }

        public TxProxyBuilder withFrom(String from) {
            this.from = from;
            return this;
        }

        public TxProxyBuilder withV(String v) {
            this.v = v;
            return this;
        }

        public TxProxyBuilder withInput(String input) {
            this.input = input;
            return this;
        }

        public TxProxyBuilder withS(String s) {
            this.s = s;
            return this;
        }

        public TxProxyBuilder withR(String r) {
            this.r = r;
            return this;
        }

        public TxProxyBuilder withNonce(Long nonce) {
            this.nonce = nonce;
            return this;
        }

        public TxProxyBuilder withValue(String value) {
            this.value = value;
            return this;
        }

        public TxProxyBuilder withGas(BigInteger gas) {
            this.gas = gas;
            return this;
        }

        public TxProxyBuilder withGasPrice(BigInteger gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public TxProxyBuilder withBlockHash(String blockHash) {
            this.blockHash = blockHash;
            return this;
        }

        public TxProxyBuilder withBlockNumber(Long blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public TxProxy build() {
            TxProxy txProxy = new TxProxy();
            txProxy.input = this.input;
            if (this.gas != null) {
                txProxy.gas = String.valueOf(this.gas);
                txProxy._gas = this.gas;
            }
            txProxy.s = this.s;
            txProxy.blockHash = this.blockHash;
            txProxy.to = this.to;
            txProxy.r = this.r;
            txProxy.transactionIndex = String.valueOf(this.transactionIndex);
            txProxy._nonce = this.nonce;
            txProxy.value = this.value;
            txProxy.v = this.v;
            txProxy.from = this.from;
            txProxy.nonce = String.valueOf(this.nonce);
            txProxy._transactionIndex = this.transactionIndex;
            txProxy.blockNumber = String.valueOf(this.blockNumber);
            txProxy._blockNumber = this.blockNumber;
            txProxy.hash = this.hash;
            if (this.gasPrice != null) {
                txProxy.gasPrice = String.valueOf(this.gasPrice);
                txProxy._gasPrice = this.gasPrice;
            }
            return txProxy;
        }
    }
}
