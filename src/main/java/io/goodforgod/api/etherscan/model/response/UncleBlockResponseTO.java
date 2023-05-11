package io.goodforgod.api.etherscan.model.response;

import io.goodforgod.api.etherscan.model.BlockUncle;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class UncleBlockResponseTO extends BaseResponseTO {

    private BlockUncle result;

    public BlockUncle getResult() {
        return result;
    }
}
