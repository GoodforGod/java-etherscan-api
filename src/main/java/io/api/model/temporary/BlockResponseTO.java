package io.api.model.temporary;

import io.api.model.Block;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class BlockResponseTO extends BaseReponseTO {

    private List<Block> result;

    public List<Block> getResult() {
        return result;
    }
}
