package io.goodforgod.api.etherscan.model.proxy.utility;

import java.util.Objects;

/**
 * @author GoodforGod
 * @since 31.10.2018
 */
public class StringProxyTO extends BaseProxyTO {

    private String result;

    public String getResult() {
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
        StringProxyTO that = (StringProxyTO) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), result);
    }

    @Override
    public String toString() {
        return "StringProxyTO{" +
                "result=" + result +
                '}';
    }
}
