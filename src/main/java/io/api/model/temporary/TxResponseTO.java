package io.api.model.temporary;

import io.api.model.Tx;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.10.2018
 */
public class TxResponseTO extends BaseReponseTO {

    private List<Tx> result;

    public List<Tx> getResult() {
        return result;
    }
}
