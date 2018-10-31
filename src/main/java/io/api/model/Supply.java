package io.api.model;

import java.math.BigInteger;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class Supply extends Wei {

    public Supply() {
        super();
    }

    public Supply(long value) {
        super(value);
    }

    public Supply(BigInteger value) {
        super(value);
    }
}
