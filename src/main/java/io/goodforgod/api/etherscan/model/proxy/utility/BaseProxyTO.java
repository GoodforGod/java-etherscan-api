package io.goodforgod.api.etherscan.model.proxy.utility;

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
}
