package io.goodforgod.api.etherscan.model.proxy.utility;

import io.goodforgod.api.etherscan.model.proxy.BlockProxy;
import java.util.Objects;

/**
 * @author GoodforGod
 * @since 01.11.2018
 */
public class BlockProxyTO extends BaseProxyTO {

    private BlockProxy result;

    public BlockProxy getResult() {
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
        BlockProxyTO that = (BlockProxyTO) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), result);
    }

    @Override
    public String toString() {
        return "BlockProxyTO{" +
                "result=" + result +
                '}';
    }
}
