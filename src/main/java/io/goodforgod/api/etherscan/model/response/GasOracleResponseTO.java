package io.goodforgod.api.etherscan.model.response;

import io.goodforgod.api.etherscan.model.GasOracle;

/**
 * @author Abhay Gupta
 * @since 14.11.2022
 */
public class GasOracleResponseTO extends BaseResponseTO {

    private GasOracle result;

    public GasOracle getResult() {
        return result;
    }
}
