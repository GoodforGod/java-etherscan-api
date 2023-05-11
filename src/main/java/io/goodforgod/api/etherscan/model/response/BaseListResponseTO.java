package io.goodforgod.api.etherscan.model.response;

import java.util.List;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public abstract class BaseListResponseTO<T> extends BaseResponseTO {

    private List<T> result;

    public List<T> getResult() {
        return result;
    }
}
