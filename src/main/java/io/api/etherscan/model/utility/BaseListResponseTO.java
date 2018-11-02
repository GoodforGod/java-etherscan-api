package io.api.etherscan.model.utility;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public abstract class BaseListResponseTO<T> extends BaseResponseTO {

    private List<T> result;

    public List<T> getResult() {
        return result;
    }
}
