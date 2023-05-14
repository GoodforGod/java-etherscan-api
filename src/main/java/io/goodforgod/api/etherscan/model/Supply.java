package io.goodforgod.api.etherscan.model;

import java.math.BigInteger;

/**
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Supply extends Wei {

    public Supply(long value) {
        super(value);
    }

    public Supply(BigInteger value) {
        super(value);
    }
}
