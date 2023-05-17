package io.goodforgod.api.etherscan.model.response;

import io.goodforgod.api.etherscan.model.EthSupply;

/**
 * @author GoodforGod
 * @since 14.05.2023
 */
public class EthSupplyResponseTO extends BaseResponseTO {

    private EthSupply result;

    public EthSupply getResult() {
        return result;
    }
}
