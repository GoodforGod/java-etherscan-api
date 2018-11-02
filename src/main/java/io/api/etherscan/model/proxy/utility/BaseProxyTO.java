package io.api.etherscan.model.proxy.utility;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
abstract class BaseProxyTO {

    private String id;
    private String jsonrpc;

    public String getId() {
        return id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }
}
