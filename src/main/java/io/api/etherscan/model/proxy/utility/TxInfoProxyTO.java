package io.api.etherscan.model.proxy.utility;

import io.api.etherscan.model.proxy.ReceiptProxy;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
public class TxInfoProxyTO extends BaseProxyTO {

    private ReceiptProxy result;

    public ReceiptProxy getResult() {
        return result;
    }
}
