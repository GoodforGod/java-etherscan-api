package io.goodforgod.api.etherscan.model;

import java.math.BigInteger;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Supply extends Wei {

    public Supply(BigInteger value) {
        super(value);
    }
}
