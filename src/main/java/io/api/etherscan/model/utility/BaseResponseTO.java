package io.api.etherscan.model.utility;

import io.api.etherscan.util.BasicUtils;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.10.2018
 */
public abstract class BaseResponseTO {

    private String status;
    private String message;

    public int getStatus() {
        return BasicUtils.isEmpty(status) ? -1 : Integer.parseInt(status);
    }

    public String getMessage() {
        return message;
    }
}
