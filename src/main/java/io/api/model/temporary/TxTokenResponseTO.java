package io.api.model.temporary;

import io.api.model.TxToken;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.10.2018
 */
public class TxTokenResponseTO extends BaseReponseTO {

    private List<TxToken> result;

    public List<TxToken> getResult() {
        return result;
    }
}
