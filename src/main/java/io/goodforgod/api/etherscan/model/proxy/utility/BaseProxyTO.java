package io.goodforgod.api.etherscan.model.proxy.utility;

import java.util.Objects;

/**
 * @author GoodforGod
 * @since 31.10.2018
 */
public abstract class BaseProxyTO {

    private String id;
    private String jsonrpc;
    private ErrorProxyTO error;

    public String getId() {
        return id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public ErrorProxyTO getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BaseProxyTO that = (BaseProxyTO) o;
        return Objects.equals(id, that.id) && Objects.equals(jsonrpc, that.jsonrpc) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jsonrpc, error);
    }

    @Override
    public String toString() {
        return "BaseProxyTO{" +
                "id=" + id +
                ", jsonrpc=" + jsonrpc +
                ", error=" + error +
                '}';
    }
}
