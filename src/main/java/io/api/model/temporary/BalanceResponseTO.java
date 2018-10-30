package io.api.model.temporary;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.10.2018
 */
public class BalanceResponseTO extends BaseReponseTO {

    private List<BalanceTO> balances;

    public List<BalanceTO> getBalances() {
        return balances;
    }
}
