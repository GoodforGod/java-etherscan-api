package io.goodforgod.api.etherscan.proxy;

import io.goodforgod.api.etherscan.ApiRunner;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 13.11.2018
 */
class ProxyBlockLastNoApiTest extends ApiRunner {

    @Test
    void correct() {
        long noLast = getApi().proxy().blockNoLast();
        assertNotEquals(0, noLast);
    }
}
