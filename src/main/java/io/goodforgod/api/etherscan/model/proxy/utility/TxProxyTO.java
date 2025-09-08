package io.goodforgod.api.etherscan.model.proxy.utility;

import io.goodforgod.api.etherscan.model.proxy.TxProxy;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 01.11.2018
 */
public class TxProxyTO extends BaseProxyTO {

    private TxProxy result;

    public TxProxy getResult() {
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
        TxProxyTO txProxyTO = (TxProxyTO) o;
        return Objects.equals(result, txProxyTO.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), result);
    }

    @Override
    public String toString() {
        return "TxProxyTO{" +
                "result=" + result +
                '}';
    }
}
