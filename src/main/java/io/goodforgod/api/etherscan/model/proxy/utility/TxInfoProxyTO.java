package io.goodforgod.api.etherscan.model.proxy.utility;

import io.goodforgod.api.etherscan.model.proxy.ReceiptProxy;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
public class TxInfoProxyTO extends BaseProxyTO {

    private ReceiptProxy result;

    public ReceiptProxy getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        TxInfoProxyTO that = (TxInfoProxyTO) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), result);
    }

    @Override
    public String toString() {
        return "TxInfoProxyTO{" +
                "result=" + result +
                '}';
    }
}
