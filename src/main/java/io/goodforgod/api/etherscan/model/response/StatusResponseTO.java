package io.goodforgod.api.etherscan.model.response;

import io.goodforgod.api.etherscan.model.Status;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class StatusResponseTO extends BaseResponseTO {

    private Status result;

    public Status getResult() {
        return result;
    }
}
