package io.goodforgod.api.etherscan.model.response;

import io.goodforgod.api.etherscan.model.Price;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class PriceResponseTO extends BaseResponseTO {

    private Price result;

    public Price getResult() {
        return result;
    }
}
