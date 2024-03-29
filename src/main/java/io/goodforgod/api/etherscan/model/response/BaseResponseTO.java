package io.goodforgod.api.etherscan.model.response;

import io.goodforgod.api.etherscan.util.BasicUtils;

/**
 * @author GoodforGod
 * @since 29.10.2018
 */
public abstract class BaseResponseTO {

    String status;
    String message;

    public int getStatus() {
        return BasicUtils.isEmpty(status)
                ? -1
                : Integer.parseInt(status);
    }

    public String getMessage() {
        return message;
    }
}
