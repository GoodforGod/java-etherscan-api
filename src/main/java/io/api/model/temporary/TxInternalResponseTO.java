package io.api.model.temporary;

import io.api.model.TxInternal;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.10.2018
 */
public class TxInternalResponseTO extends BaseReponseTO {

    private List<TxInternal> result;

    public List<TxInternal> getResult() {
        return result;
    }
}
