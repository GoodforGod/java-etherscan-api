package io.goodforgod.api.etherscan.model.proxy;

import com.google.gson.annotations.Expose;
import io.goodforgod.api.etherscan.model.Wei;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * @author GoodforGod
 * @since 31.10.2018
 */
public class TxProxy implements Comparable<TxProxy> {

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
    private Wei _gas;
    private String gasPrice;
    @Expose(deserialize = false, serialize = false)
    private Wei _gasPrice;
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

    public Wei getGas() {
        if (_gas == null && !BasicUtils.isEmpty(gas))
            _gas = Wei.ofWei(BasicUtils.parseHex(gas));
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

    public Wei getGasPrice() {
        if (_gasPrice == null && !BasicUtils.isEmpty(gasPrice))
            _gasPrice = Wei.ofWei(BasicUtils.parseHex(gasPrice));
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
    public int compareTo(@NotNull TxProxy o) {
        final int firstCompare = Long.compare(getBlockNumber(), o.getBlockNumber());
        return (firstCompare == 0)
                ? Long.compare(getTransactionIndex(), o.getTransactionIndex())
                : firstCompare;
    }

    public static TxProxyBuilder builder() {
        return new TxProxyBuilder();
    }

    public static class TxProxyBuilder {

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
        private Wei gas;
        private Wei gasPrice;
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

        public TxProxyBuilder withGas(Wei gas) {
            this.gas = gas;
            return this;
        }

        public TxProxyBuilder withGasPrice(Wei gasPrice) {
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
                txProxy._gasPrice = this.gasPrice;
            }
            return txProxy;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TxProxy txProxy = (TxProxy) o;
        return Objects.equals(to, txProxy.to) && Objects.equals(hash, txProxy.hash)
                && Objects.equals(transactionIndex, txProxy.transactionIndex)
                && Objects.equals(_transactionIndex, txProxy._transactionIndex) && Objects.equals(from, txProxy.from)
                && Objects.equals(v, txProxy.v) && Objects.equals(input, txProxy.input) && Objects.equals(s, txProxy.s)
                && Objects.equals(r, txProxy.r) && Objects.equals(nonce, txProxy.nonce) && Objects.equals(_nonce, txProxy._nonce)
                && Objects.equals(value, txProxy.value) && Objects.equals(gas, txProxy.gas) && Objects.equals(_gas, txProxy._gas)
                && Objects.equals(gasPrice, txProxy.gasPrice) && Objects.equals(_gasPrice, txProxy._gasPrice)
                && Objects.equals(blockHash, txProxy.blockHash) && Objects.equals(blockNumber, txProxy.blockNumber)
                && Objects.equals(_blockNumber, txProxy._blockNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, hash, transactionIndex, transactionIndex, from, v, input, s, r, nonce, nonce, value, gas, gas,
                gasPrice, gasPrice, blockHash, blockNumber, blockNumber);
    }

    @Override
    public String toString() {
        return "TxProxy{" +
                "to=" + to + '\'' +
                ", hash=" + hash + '\'' +
                ", transactionIndex=" + transactionIndex + '\'' +
                ", transactionIndex=" + _transactionIndex +
                ", from=" + from + '\'' +
                ", v=" + v + '\'' +
                ", input=" + input + '\'' +
                ", s=" + s + '\'' +
                ", r=" + r + '\'' +
                ", nonce=" + nonce + '\'' +
                ", nonce=" + _nonce +
                ", value=" + value + '\'' +
                ", gas=" + gas + '\'' +
                ", gas=" + _gas +
                ", gasPrice=" + gasPrice + '\'' +
                ", gasPrice=" + _gasPrice +
                ", blockHash=" + blockHash + '\'' +
                ", blockNumber=" + blockNumber + '\'' +
                ", blockNumber=" + _blockNumber +
                '}';
    }
}
