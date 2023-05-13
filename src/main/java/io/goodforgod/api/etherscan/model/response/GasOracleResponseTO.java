package io.api.etherscan.model.utility;

import io.api.etherscan.model.GasOracle;

/**
 * ! NO DESCRIPTION !
 *
 * @author Abhay Gupta
 * @since 14.11.2022
 */
public class GasOracleResponseTO extends BaseResponseTO {

    private GasOracle result;

    public GasOracle getResult() {
        return result;
    }
}
